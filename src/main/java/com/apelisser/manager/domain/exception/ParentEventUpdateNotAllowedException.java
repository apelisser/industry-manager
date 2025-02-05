package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class ParentEventUpdateNotAllowedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 371220506777063915L;

    private final String eventName;

    public ParentEventUpdateNotAllowedException(String eventName) {
        this.eventName = eventName;
    }

    public ParentEventUpdateNotAllowedException(String eventName, String message) {
        super(message);
        this.eventName = eventName;
    }

    public ParentEventUpdateNotAllowedException(String eventName, String message, Throwable cause) {
        super(message, cause);
        this.eventName = eventName;
    }

    public ParentEventUpdateNotAllowedException(String eventName, Throwable cause) {
        super(cause);
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

}
