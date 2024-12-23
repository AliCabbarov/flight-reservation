package ingress.flightms.service.impl;

import ingress.common.config.JwtSessionData;
import ingress.common.model.dto.TicketMailDto;
import ingress.common.model.exception.ApplicationException;
import ingress.flightms.model.dto.response.BookingSearchResponseDto;
import ingress.flightms.model.dto.response.FlightResponseDto;
import ingress.flightms.model.entity.Flight;
import ingress.flightms.model.enums.TicketStatus;
import ingress.flightms.repository.FlightPlanePlaceRepository;
import ingress.flightms.repository.FlightRepository;
import ingress.flightms.repository.PlanePlaceRepository;
import ingress.flightms.repository.TicketRepository;
import ingress.flightms.service.BookingService;
import ingress.flightms.service.kafka.KafkaProducerService;
import ingress.flightms.specification.FlightSpecification;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ingress.flightms.model.enums.Exceptions.NOT_FOUND;
import static ingress.flightms.util.FileUtil.createCancelFlightContent;
import static ingress.flightms.util.MapperUtil.mapDto;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);
    private final FlightSpecification flightSpecification;
    private final FlightRepository flightRepository;
    private final PlanePlaceRepository planePlaceRepository;
    private final FlightPlanePlaceRepository flightPlanePlaceRepository;
    private final TicketRepository ticketRepository;
    private final KafkaProducerService kafkaProducerService;
    private final JwtSessionData jwtSessionData;

    @Override
    public List<BookingSearchResponseDto> search(String from, String to, String date, BigDecimal price) {
        Specification<Flight> readySpecification = flightSpecification.search(from, to, convertToDateTime(date), price);
        List<Flight> flights = flightRepository.findAll(readySpecification);
        List<BookingSearchResponseDto> res = new ArrayList<>(flights.size());
        mapDto(flights, res);
        return res;
    }

    @Override
    public FlightResponseDto availableSeats(Long flightId) {
        Flight flight = flightRepository.findByIdAndStatus(flightId, true).orElseThrow(() -> new ApplicationException(NOT_FOUND, Flight.class.getSimpleName()));
        List<Integer> capturedSeats = flightPlanePlaceRepository.findPlaceNumberByFlightId(flightId);
        List<Map<String, Object>> availableSeats = planePlaceRepository.findPlanePlaceByFlightId(flight.getPlane().getId(), capturedSeats);

        return FlightResponseDto.builder()
                .from(flight.getFrom())
                .to(flight.getTo())
                .price(flight.getPrice())
                .ticketCount(flight.getTicketCount())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .availableSeats(availableSeats)
                .build();
    }

    private LocalDate convertToDateTime(String date) {
        if (date != null && !date.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                log.error("Invalid date format: {}", date, e);
                return null;
            }
        }
        return null;
    }

    @Override
    public void cancelFlight(Long flightId) {
        Flight flight = flightRepository.findByIdAndStatus(flightId, true).orElseThrow(() -> new ApplicationException(NOT_FOUND, Flight.class.getSimpleName()));
        flight.setStatus(false);
        flightPlanePlaceRepository.findByStatusAndFlight(true, flight).forEach(flightPlanePlace -> {
            flightPlanePlace.setStatus(false);
            flightPlanePlaceRepository.save(flightPlanePlace);
        });
        ticketRepository.findByStatusAndFlightAndTicketStatus(true,flight, TicketStatus.CONFIRMED).forEach(ticket -> {
            ticket.setStatus(false);
            ticketRepository.save(ticket);
            kafkaProducerService.sendTicketContent(new TicketMailDto(createCancelFlightContent(ticket),jwtSessionData.getUsername(),"Ticket Cancelled"));
        });
        flightRepository.save(flight);
    }
}
