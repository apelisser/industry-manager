package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class ConstraintViolationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 881846956119271311L;

    public ConstraintViolationException(String message) {
        super(message);
    }

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstraintViolationException(Throwable cause) {
        super(cause);
    }

}
