package ingress.flightms.service;


import ingress.flightms.model.dto.request.PlaneRequestDTO;
import ingress.flightms.model.dto.response.PlaneAllResponseDto;
import ingress.flightms.model.dto.response.PlaneResponseDTO;

import java.util.Set;

public interface PlaneService {

    Long createPlane(PlaneRequestDTO planeRequestDTO);
    PlaneResponseDTO updatePlane(Long id, PlaneRequestDTO planeRequestDTO);
    void deletePlane(Long id);
    PlaneResponseDTO getPlane(Long id);
    Set<PlaneAllResponseDto> getAllPlanes();

}
