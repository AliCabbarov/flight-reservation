package ingress.flightms.model.dto.response;

import ingress.flightms.model.enums.Airport;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class FlightResponseDto {
    private Airport from;
    private Airport to;
    private BigDecimal price;
    private Integer ticketCount;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private List<Map<String,Object>> availableSeats;
}
