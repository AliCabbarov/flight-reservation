package ingress.flightms.config.annotation;

import ingress.flightms.model.enums.Airport;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.stream.Stream;

public class AirportCodeValidator implements ConstraintValidator<ValidAirportCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() != 3) {
            return false;
        }
        return Stream.of(Airport.values())
                .anyMatch(airport -> airport.name().equals(value));
    }
}
