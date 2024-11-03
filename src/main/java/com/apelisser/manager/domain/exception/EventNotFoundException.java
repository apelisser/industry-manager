package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class EventNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = -1088455508172676797L;

    private final Long id;

    public EventNotFoundException(Long id) {
        this.id = id;
    }

    public EventNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }

    public EventNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public EventNotFoundException(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
