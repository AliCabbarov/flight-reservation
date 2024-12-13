package ingress.flightms.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ingress.flightms.model.dto.response.PlaneResponseDTO;
import ingress.flightms.model.enums.ApprovalState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightDtoByCreatedOperator {
    Long operatorId;
    String operatorName;
    String operatorSurname;
    String operatorEmail;
    FlightDto flightDto;
}
