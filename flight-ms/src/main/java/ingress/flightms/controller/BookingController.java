package ingress.flightms.controller;

import ingress.flightms.model.dto.response.BookingSearchResponseDto;
import ingress.flightms.model.dto.response.FlightResponseDto;
import ingress.flightms.service.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
@Validated
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/search")
    public ResponseEntity<List<BookingSearchResponseDto>> search(
            @RequestParam(required = false) String to,
            @RequestParam(required = false) BigDecimal initialPrice,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String departureDate) {
        return ResponseEntity.ok(bookingService.search(to, from, departureDate, initialPrice));
    }
    @GetMapping("/available-seats/{id}")
    public ResponseEntity<FlightResponseDto> availableSeats(@PathVariable(value = "id") Long flightId) {
        return ResponseEntity.ok(bookingService.availableSeats(flightId));
    }
}
