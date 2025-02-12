package ingress.flightms.service;

import ingress.flightms.model.dto.request.AirlineDto;
import ingress.flightms.model.dto.response.AirlineResponseDto;

import java.util.List;

public interface AirlineService {
    AirlineResponseDto createAirline(AirlineDto airline);

    AirlineResponseDto findById(long id);

    List<AirlineResponseDto> findAll();

    AirlineDto findByAirlineByName(String name);

    AirlineResponseDto updateAirline(long id, AirlineDto airline);

    void deleteAirline(long id);
}
