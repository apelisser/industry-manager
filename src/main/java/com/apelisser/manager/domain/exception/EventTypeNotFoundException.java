package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class EventTypeNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = -1065764743637296515L;

    private final Long id;

    public EventTypeNotFoundException(Long id) {
        this.id = id;
    }

    public EventTypeNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }

    public EventTypeNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public EventTypeNotFoundException(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
