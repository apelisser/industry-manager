package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class IndustryManagerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3009798772049459735L;

    public IndustryManagerException() {
    }

    public IndustryManagerException(String message) {
        super(message);
    }

    public IndustryManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndustryManagerException(Throwable cause) {
        super(cause);
    }

    public IndustryManagerException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
