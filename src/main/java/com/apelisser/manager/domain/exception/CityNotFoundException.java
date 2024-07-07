package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class CityNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 8593619600870189919L;

    private final Long id;

    public CityNotFoundException(Long id) {
        this.id = id;
    }

    public CityNotFoundException(Long id,String message) {
        super(message);
        this.id = id;
    }

    public CityNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public CityNotFoundException(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
