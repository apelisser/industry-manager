package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class PersonNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 3995201305866419805L;

    private final Long id;

    public PersonNotFoundException(Long id) {
        this.id = id;
    }

    public PersonNotFoundException(Long id,String message) {
        super(message);
        this.id = id;
    }

    public PersonNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public PersonNotFoundException(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
