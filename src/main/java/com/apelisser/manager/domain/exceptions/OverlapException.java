package com.apelisser.manager.domain.exceptions;

import java.io.Serial;

public class OverlapException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 6078778396309432454L;

    public OverlapException(String message) {
        super(message);
    }

    public OverlapException(String message, Throwable cause) {
        super(message, cause);
    }

}
