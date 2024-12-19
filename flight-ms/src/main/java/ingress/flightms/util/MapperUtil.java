package ingress.flightms.util;

import ingress.flightms.model.dto.response.BookingSearchResponseDto;
import ingress.flightms.model.entity.Flight;

import java.util.List;

public class MapperUtil {
    public static void mapDto(List<Flight> flights, List<BookingSearchResponseDto> res) {
        flights.forEach(flight -> {
            BookingSearchResponseDto bookingSearchResponseDto = new BookingSearchResponseDto();
            bookingSearchResponseDto.setId(flight.getId());
            bookingSearchResponseDto.setFrom((flight.getFrom() + " - " + flight.getFrom().getCity() + ", " + flight.getFrom().getCountry()));
            bookingSearchResponseDto.setTo((flight.getTo() + " - " + flight.getTo().getCity() + ", " + flight.getTo().getCountry()));
            bookingSearchResponseDto.setPrice(flight.getPrice());
            bookingSearchResponseDto.setDepartureTime(flight.getDepartureTime());
            bookingSearchResponseDto.setArrivalTime(flight.getArrivalTime());
            res.add(bookingSearchResponseDto);
        });
    }


}
