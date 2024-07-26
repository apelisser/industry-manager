package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class PositionNotFoundExeption extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 8329047397434170691L;

    private final Long id;

    public PositionNotFoundExeption(Long id) {
        this.id = id;
    }

    public PositionNotFoundExeption(Long id,String message) {
        super(message);
        this.id = id;
    }

    public PositionNotFoundExeption(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public PositionNotFoundExeption(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
