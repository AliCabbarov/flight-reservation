package ingress.flightms.service;

import ingress.flightms.model.dto.response.BookingSearchResponseDto;
import ingress.flightms.model.dto.response.FlightResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface BookingService {
    List<BookingSearchResponseDto> search(String from, String to, String date, BigDecimal price);

    FlightResponseDto availableSeats(Long flightId);

    void cancelFlight(Long flightId);
}
