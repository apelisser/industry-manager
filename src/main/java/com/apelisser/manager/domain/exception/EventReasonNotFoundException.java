package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class EventReasonNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = -3162027457122775552L;

    private final Long id;

    public EventReasonNotFoundException(Long id) {
        this.id = id;
    }

    public EventReasonNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }

    public EventReasonNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public EventReasonNotFoundException(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
