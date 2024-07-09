package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class CompanyNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 2899976668186123675L;

    private final Long id;

    public CompanyNotFoundException(Long id) {
        this.id = id;
    }

    public CompanyNotFoundException(Long id,String message) {
        super(message);
        this.id = id;
    }

    public CompanyNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public CompanyNotFoundException(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
