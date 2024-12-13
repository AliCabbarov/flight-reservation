package ingress.flightms.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlightPlanePlaceDto {
    private Long flightId;
    private Long planePlaceId;
    private Long ticketId;
    private String placeStatus;

}