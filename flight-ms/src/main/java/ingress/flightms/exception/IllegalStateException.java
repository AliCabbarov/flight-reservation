package ingress.flightms.exception;

import ingress.common.model.exception.AbstractException;
import ingress.common.model.exception.ApplicationException;

public class IllegalStateException extends ApplicationException {
    public IllegalStateException(AbstractException exceptions, Object... args) {
        super(exceptions, args);
    }
}
