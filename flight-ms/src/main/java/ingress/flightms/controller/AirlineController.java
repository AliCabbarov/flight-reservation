package ingress.flightms.controller;

import ingress.flightms.model.dto.request.AirlineDto;
import ingress.flightms.model.dto.response.AirlineResponseDto;
import ingress.flightms.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/airlines")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;

    @PostMapping
    public AirlineResponseDto createAirline(@RequestBody @Validated AirlineDto airline) {
        return airlineService.createAirline(airline);
    }

    @GetMapping("/{id}")
    public AirlineResponseDto findById(@PathVariable long id) {
        return airlineService.findById(id);
    }

    @GetMapping
    public List<AirlineResponseDto> findAll() {
        return airlineService.findAll();
    }

    @PutMapping("{id}")
    public AirlineResponseDto updateAirline(@PathVariable long id, @RequestBody @Validated AirlineDto airline) {
        return airlineService.updateAirline(id, airline);
    }

    @GetMapping("/find-name/{name}")
    public AirlineDto findByName(@PathVariable String name) {
        return airlineService.findByAirlineByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteAirline(@PathVariable long id) {
        airlineService.deleteAirline(id);
    }
}
