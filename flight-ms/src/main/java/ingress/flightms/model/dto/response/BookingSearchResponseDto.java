package ingress.flightms.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingSearchResponseDto {
    private Long id;
    private String from;
    private String to;
    private BigDecimal price;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
