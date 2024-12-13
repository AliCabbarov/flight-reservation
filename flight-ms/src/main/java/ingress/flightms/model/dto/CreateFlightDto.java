package ingress.flightms.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateFlightDto {

    @NotNull(message = "From airport is required")
    @Size(min = 3, max = 3, message = "From airport code must be exactly 3 characters")
    private String from;

    @NotNull(message = "To airport is required")
    @Size(min = 3, max = 3, message = "To airport code must be exactly 3 characters")
    private String to;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal price;

    @Min(value = 1, message = "Ticket count must be at least 1")
    @NotNull(message = "Departure time is required")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Plane ID is required")
    private Long planeId;

    private String feedbackMessage;

}
