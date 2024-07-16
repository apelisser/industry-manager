package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class DepartmentNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = -6146725120330073481L;

    private final Long id;

    public DepartmentNotFoundException(Long id) {
        this.id = id;
    }

    public DepartmentNotFoundException(Long id,String message) {
        super(message);
        this.id = id;
    }

    public DepartmentNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public DepartmentNotFoundException(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
