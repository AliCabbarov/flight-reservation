package ingress.flightms.service;


import ingress.flightms.model.dto.request.FlightPlanePlaceDto;

import java.util.List;

public interface FlightPlanePlaceService  {

    FlightPlanePlaceDto getById(Long id);
    List<FlightPlanePlaceDto> getAll();
    FlightPlanePlaceDto update(Long id , FlightPlanePlaceDto dto);
    void delete(Long id);
}
