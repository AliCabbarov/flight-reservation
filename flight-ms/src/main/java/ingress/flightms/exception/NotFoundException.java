package ingress.flightms.exception;

import ingress.common.model.exception.AbstractException;
import ingress.common.model.exception.ApplicationException;

public class NotFoundException extends ApplicationException {
    public NotFoundException(AbstractException exceptions, Object... args) {
        super(exceptions, args);
    }
}
