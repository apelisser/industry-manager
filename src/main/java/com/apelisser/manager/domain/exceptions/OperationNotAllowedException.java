package com.apelisser.manager.domain.exceptions;

import java.io.Serial;

public class OperationNotAllowedException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = -6728318838828128485L;

    public OperationNotAllowedException() {
    }

    public OperationNotAllowedException(String message) {
        super(message);
    }

    public OperationNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotAllowedException(Throwable cause) {
        super(cause);
    }

}
