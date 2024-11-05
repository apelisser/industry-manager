package com.apelisser.manager.domain.exceptions;

import java.io.Serial;

public class EntityInUseException extends IndustryManagerException {

    private final Class<?> entity;
    private final Object resourceId;

    @Serial
    private static final long serialVersionUID = 58861967049976147L;

    public <T> EntityInUseException(Class<T> entity, Object resourceId, String message) {
        super(message);
        this.entity = entity;
        this.resourceId = resourceId;
    }

    public <T> EntityInUseException(Class<T> entity, Object resourceId, String message, Throwable cause) {
        super(message, cause);
        this.entity = entity;
        this.resourceId = resourceId;
    }

    public <T> EntityInUseException(Class<T> entity, Object resourceId, Throwable cause) {
        super(cause);
        this.entity = entity;
        this.resourceId = resourceId;
    }

    public Class<?> getEntityClass() {
        return entity;
    }

    public String getEntityClassName() {
        return entity.getSimpleName();
    }

    public String getIdValue() {
        return String.valueOf(resourceId);
    }

}
