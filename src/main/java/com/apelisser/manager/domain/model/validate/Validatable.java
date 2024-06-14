package com.apelisser.manager.domain.model.validate;

@FunctionalInterface
public interface Validatable<T> {

    boolean isValid(T value);
    
}
