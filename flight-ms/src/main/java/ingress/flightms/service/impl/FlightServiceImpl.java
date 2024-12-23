package ingress.flightms.service.impl;

import ingress.common.config.JwtSessionData;
import ingress.common.model.kafka.AdminNotificationDto;
import ingress.common.model.kafka.OperatorNotificationDto;
//import ingress.flightms.config.client.UserClient;
import ingress.flightms.config.client.UserClient;
import ingress.flightms.config.client.UserResponseDto;
import ingress.flightms.exception.IllegalStateException;
import ingress.flightms.exception.NotFoundException;
import ingress.flightms.mapper.FlightMapper;
import ingress.flightms.model.dto.FlightDtoByCreatedOperator;
import ingress.flightms.model.dto.request.FlightRequestDto;
import ingress.flightms.model.dto.FlightDto;
import ingress.flightms.model.entity.Flight;
import ingress.flightms.model.entity.Plane;
import ingress.flightms.model.enums.ApprovalState;
import ingress.flightms.producer.KafkaProducer;
import ingress.flightms.repository.FlightRepository;
import ingress.flightms.repository.PlaneRepository;
import ingress.flightms.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static ingress.flightms.model.enums.Exceptions.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final PlaneRepository planeRepository;
    private final FlightMapper flightMapper;
    private final JwtSessionData jwtSessionData;
    private final KafkaProducer kafkaProducer;
    private final UserClient userClient;

    @Value("${kafka.topic.admin-topic}")
    private String ADMIN_TOPIC;

    @Value("${kafka.topic.operator-topic}")
    private String OPERATOR_TOPIC;


    @Override
    public FlightDto getById(Long id) {
        log.info("Fetching flight with ID: {}", id);
        var entity = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FLIGHT_NOT_FOUND));
        log.info("Flight found: {}", entity);
        return flightMapper.toDto(entity);
    }

    @Override
    public List<FlightDto> getAll() {
        log.info("Fetching all flights");
        List<FlightDto> flights = flightRepository.findAll().stream()
                .map(flightMapper::toDto)
                .toList();
        log.info("Total flights found: {}", flights.size());
        return flights;
    }

    @Override
    public FlightDto create(FlightRequestDto dto) {
        log.info("Creating flight with details: {}", dto);
        Plane plane = planeRepository.findByIdWithPlanePlaces(dto.getPlaneId())
                .orElseThrow(() -> new NotFoundException(PLANE_NOT_FOUND, dto.getPlaneId()));

        if (plane.getPlanePlaces() == null || plane.getPlanePlaces().isEmpty()) {
            log.warn("No PlanePlaces found for Plane with ID: {}", plane.getId());
            throw new NotFoundException(PLANE_PLACES_NOT_FOUND, plane.getId());
        }
        Flight flight = flightMapper.toEntity(dto);
        flight.setPlane(plane);
        flight.setTicketCount(plane.getCapacity());
        flight.setApprovalState(ApprovalState.PENDING);

        var savedFlight = flightRepository.save(flight);
        log.info("Flight created with ID: {}", savedFlight.getId());

//        kafkaProducer.notifyAdminForApprovement(ADMIN_TOPIC,
//                new AdminNotificationDto(
//                        jwtSessionData.getUserId(),
//                        savedFlight.getId(),
//                        "Notification for admin for  flight approvement"));

        return flightMapper.toDto(savedFlight);
    }

    @Override
    public FlightDto update(Long id, FlightRequestDto dto) {
        log.info("Updating flight with ID: {}", id);
        var entity = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FLIGHT_NOT_FOUND));
        flightMapper.updateFlight(entity, dto);
        log.info("Flight updated: {}", entity);
        return flightMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting flight with ID: {}", id);
        var existedEntity = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FLIGHT_NOT_FOUND, id));
        existedEntity.setStatus(false);
        flightRepository.save(existedEntity);
        log.info("Flight with ID: {} marked as status=false", id);
    }


    @Override
    public void approveFlight(Long id, String feedback) {
        log.info("Approving flight with ID: {}", id);
        var flight = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FLIGHT_NOT_FOUND));
        if (flight.getApprovalState() != ApprovalState.PENDING) {
            log.error("Flight with ID: {} is not in PENDING state", id);
            throw new IllegalStateException(STATE_IS_NOT_PENDING, id);
        }
        String feedbackMessage = feedback != null ? feedback : "No feedback provided";
        flight.setApprovalState(ApprovalState.APPROVED);
        flight.setFeedbackMessage(feedback);
        flightRepository.save(flight);

        var operatorDto = userClient.getUserDetailsById(jwtSessionData.getTokenWithPrefix(), flight.getCreatedBy());
        log.info("Flight with ID: {} approved", id);
        OperatorNotificationDto notification = new OperatorNotificationDto(
                flight.getId(),
                operatorDto.email(),
                operatorDto.name(),
                operatorDto.surname(),
                String.valueOf(ApprovalState.APPROVED),
                "Flight approved successfully with id :" + flight.getId(),
                feedbackMessage
        );
        kafkaProducer.notifyOperator(OPERATOR_TOPIC, notification);
    }

    @Override
    public void rejectFlight(Long id, String feedback) { // feedback optional
        log.info("Rejecting flight with ID: {}", id);
        var flight = flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FLIGHT_NOT_FOUND));

        if (flight.getApprovalState() != ApprovalState.PENDING) {
            log.error("Flight with ID: {} is not in PENDING state", id);
            throw new IllegalStateException(STATE_IS_NOT_PENDING);
        }
        String feedbackMessage = feedback != null ? feedback : "No feedback provided";

        flight.setApprovalState(ApprovalState.REJECTED);
        flight.setFeedbackMessage(feedback);
        flightRepository.save(flight);
        var operatorDto = userClient.getUserDetailsById(jwtSessionData.getTokenWithPrefix(), flight.getCreatedBy());

        log.info("Flight with ID: {} rejected", id);
        OperatorNotificationDto notification = new OperatorNotificationDto(
                flight.getId(),
                operatorDto.email(),
                operatorDto.name(),
                operatorDto.surname(),
                String.valueOf(ApprovalState.REJECTED),
                "Flight rejected successfully with id :" + flight.getId(),
                feedbackMessage);
        kafkaProducer.notifyOperator(OPERATOR_TOPIC, notification);
    }

    @Override
    public List<FlightDtoByCreatedOperator> getPendingFlightsWithOperatorDetails() {
        log.info("Fetching flights with state PENDING");

        List<Flight> flights = flightRepository.findByApprovalState(ApprovalState.PENDING);
        Map<Long, UserResponseDto> res = new HashMap<>();

        flights.stream()
                .map(Flight::getCreatedBy)
                .distinct()
                .forEach(id -> {
                    try {
                        UserResponseDto responseDto = userClient.getUserDetailsById(jwtSessionData.getTokenWithPrefix(), id);
                        res.put(id, responseDto);
                    } catch (Exception e) {
                        log.error("Error while fetching user details for user with ID: {}", id, e);
                    }
                });


        List<FlightDtoByCreatedOperator> flightDtoByCreatedOperators = flights.stream()
                .filter(flight -> res.containsKey(flight.getCreatedBy()))
                .map(flight -> {
                    FlightDto flightDto = flightMapper.toDto(flight);
                    UserResponseDto operatorDto = res.get(flight.getCreatedBy());

                    return FlightDtoByCreatedOperator.builder()
                            .operatorId(operatorDto.id())
                            .operatorName(operatorDto.name())
                            .operatorSurname(operatorDto.surname())
                            .operatorEmail(operatorDto.email())
                            .flightDto(flightDto)
                            .build();
                })
                .toList();

        log.info("Total flights found with state PENDING: {}", flights.size());

        return flightDtoByCreatedOperators;
    }

    @Override
    public List<FlightDto> getFlightsByState(String state) {
        log.info("Fetching flights with state: {}", state);
        List<FlightDto> flights = flightRepository.findByApprovalState(ApprovalState.valueOf(state.toUpperCase()))
                .stream()
                .map(flightMapper::toDto)
                .toList();
        log.info("Total flights found with state {}: {}", state, flights.size());
        return flights;
    }


}
