package ingress.flightms.config.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FlightTimeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFlightTimes {
    String message() default "Arrival time must be after departure time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}