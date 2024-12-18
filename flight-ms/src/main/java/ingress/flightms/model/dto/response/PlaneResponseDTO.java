package ingress.flightms.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import ingress.flightms.model.dto.request.AirlineDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaneResponseDTO {
    private Long id;
    private String name;
    private Integer capacity;
    private AirlineDto airline;
    private Set<PlanePlaceResponse> planePlaces;
}
