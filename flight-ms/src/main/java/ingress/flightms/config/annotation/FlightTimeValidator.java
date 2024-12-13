package ingress.flightms.config.annotation;

import ingress.flightms.model.dto.request.FlightRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class FlightTimeValidator implements ConstraintValidator<ValidFlightTimes, FlightRequestDto> {

    @Override
    public boolean isValid(FlightRequestDto dto, ConstraintValidatorContext context) {
        if (dto.getDepartureTime() == null || dto.getArrivalTime() == null) {
            return true;
        }
        boolean isValid = dto.getArrivalTime().isAfter(dto.getDepartureTime());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Arrival time must be after departure time")
                    .addPropertyNode("arrivalTime")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
