package com.apelisser.manager.domain.exception;

import java.io.Serial;

public class EntityNotFoundException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = -7799526871134608256L;

    private final Class<?> entity;
    private final Object identifier;

    public <T> EntityNotFoundException(Class<?> entity, T identifier) {
        this.entity = entity;
        this.identifier = identifier;
    }

    public <T> EntityNotFoundException(Class<?> entity, T identifier, String message) {
        super(message);
        this.entity = entity;
        this.identifier = identifier;
    }

    public <T> EntityNotFoundException(Class<?> entity, T identifier, Throwable cause) {
        super(cause);
        this.entity = entity;
        this.identifier = identifier;
    }

    public <T> EntityNotFoundException(Class<?> entity, T identifier, String message, Throwable cause) {
        super(message, cause);
        this.entity = entity;
        this.identifier = identifier;
    }

    public Class<?> getEntity() {
        return entity;
    }

    public Object getIdentifier() {
        return identifier;
    }

    public String getEntityClassName() {
        return entity.getSimpleName();
    }

}
