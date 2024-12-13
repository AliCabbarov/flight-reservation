package ingress.flightms.service.impl;

import ingress.common.model.exception.ApplicationException;
import ingress.flightms.exception.NotFoundException;
import ingress.flightms.model.dto.request.AirlineDto;
import ingress.flightms.model.dto.response.AirlineResponseDto;
import ingress.flightms.model.entity.Airline;
import ingress.flightms.model.enums.Exceptions;
import ingress.flightms.repository.AirlineRepository;
import ingress.flightms.service.AirlineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final ModelMapper modelMapper;

    @Override
    public AirlineResponseDto createAirline(AirlineDto airlineDto) {
        Airline airline = modelMapper.map(airlineDto, Airline.class);
        try {
            Airline airlineNew = airlineRepository.save(airline);
            return new AirlineResponseDto(airlineNew.getName(), airlineNew.getId());
        } catch (Exception e) {
            throw new ApplicationException(Exceptions.UNIQUE_CONSTRAINT, airlineDto.getName());
        }
    }

    @Override
    public AirlineResponseDto findById(long id) {
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Exceptions.NOT_FOUND, "Airline not found with id : " + id));
        return modelMapper.map(airline, AirlineResponseDto.class);
    }

    @Override
    public List<AirlineResponseDto> findAll() {
        List<Airline> airlines = airlineRepository.findAll();
        return airlines.stream()
                .map(airline -> modelMapper.map(airline, AirlineResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AirlineDto findByAirlineByName(String name) {
        Airline airline = airlineRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(Exceptions.NOT_FOUND, "Airline not found with name : " + name));
        return modelMapper.map(airline, AirlineDto.class);
    }

    @Override
    public AirlineResponseDto updateAirline(long id, AirlineDto airline) {
        Airline existingAirline = airlineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Exceptions.NOT_FOUND, "Airline not found with id: " + id));
        existingAirline.setName(airline.getName());
        Airline updatedAirline = airlineRepository.save(existingAirline);
        log.info("Airline updated with ID: {}", updatedAirline.getId());
        return modelMapper.map(updatedAirline, AirlineResponseDto.class);
    }

    @Override
    public void deleteAirline(long id) {
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Exceptions.NOT_FOUND, "Airline not found with id " + id));
        airline.setStatus(false);
    }
}
