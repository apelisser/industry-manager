package com.apelisser.manager.domain.services;

@FunctionalInterface
public interface Validatable<T> {

    void validate(T t);

}
