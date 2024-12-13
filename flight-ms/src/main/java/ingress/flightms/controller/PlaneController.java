package ingress.flightms.controller;


import ingress.common.model.exception.ApplicationException;
import ingress.flightms.model.dto.request.PlaneRequestDTO;
import ingress.flightms.model.dto.response.PlaneAllResponseDto;
import ingress.flightms.model.dto.response.PlaneResponseDTO;
import ingress.flightms.model.enums.Exceptions;
import ingress.flightms.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/planes")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @GetMapping
    public ResponseEntity<Set<PlaneAllResponseDto>> getAllPlanes() {
        Set<PlaneAllResponseDto> allPlanes = planeService.getAllPlanes();
        return ResponseEntity.ok(allPlanes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaneResponseDTO> getPlaneById(@PathVariable Long id) {
        PlaneResponseDTO plane = planeService.getPlane(id);
        return ResponseEntity.ok(plane);
    }

    @PostMapping
    public ResponseEntity<Long> createPlane(@RequestBody PlaneRequestDTO planeRequestDTO) {
        Long id = planeService.createPlane(planeRequestDTO);
        return ResponseEntity.created(URI.create("/v1/plane" + id)).body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaneResponseDTO> updatePlane(@PathVariable Long id, @RequestBody PlaneRequestDTO planeRequestDTO) {

        PlaneResponseDTO plane = planeService.updatePlane(id, planeRequestDTO);
        return ResponseEntity.ok(plane);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable Long id) {

        planeService.deletePlane(id);
        return ResponseEntity.ok().build();
    }
}
