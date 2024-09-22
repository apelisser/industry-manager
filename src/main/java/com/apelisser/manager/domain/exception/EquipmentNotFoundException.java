package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class EquipmentNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 9193391793844949259L;

    private final Long id;

    public EquipmentNotFoundException(Long id) {
        this.id = id;
    }

    public EquipmentNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }

    public EquipmentNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

    public EquipmentNotFoundException(Long id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
