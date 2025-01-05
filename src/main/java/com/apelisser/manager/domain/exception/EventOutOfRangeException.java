package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class EventOutOfRangeException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 1996060315947211739L;

    public EventOutOfRangeException(String message) {
        super(message);
    }

    public EventOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

}
