package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class OutOfRangeException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 1996060315947211739L;

    public OutOfRangeException(String message) {
        super(message);
    }

    public OutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

}
