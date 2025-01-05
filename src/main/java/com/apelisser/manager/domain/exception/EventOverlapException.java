package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class EventOverlapException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 6078778396309432454L;

    public EventOverlapException(String message) {
        super(message);
    }

    public EventOverlapException(String message, Throwable cause) {
        super(message, cause);
    }

}
