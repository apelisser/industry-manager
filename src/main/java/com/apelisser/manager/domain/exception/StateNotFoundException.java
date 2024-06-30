package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class StateNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 8035763556392510944L;

    private final Long id;

    public StateNotFoundException(Long id) {
        this.id = id;
    }

    public StateNotFoundException(Long id,String message) {
        super(message);
        this.id = id;
    }

    public StateNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public StateNotFoundException(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
