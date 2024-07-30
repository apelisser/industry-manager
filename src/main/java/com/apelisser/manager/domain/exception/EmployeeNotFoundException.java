package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class EmployeeNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = -6146725120330073481L;

    private final Long id;

    public EmployeeNotFoundException(Long id) {
        this.id = id;
    }

    public EmployeeNotFoundException(Long id,String message) {
        super(message);
        this.id = id;
    }

    public EmployeeNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public EmployeeNotFoundException(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
