package com.apelisser.manager.domain.enums;

import com.apelisser.manager.domain.util.validation.Validatable;
import com.apelisser.manager.domain.util.validation.impl.CNPJValidator;
import com.apelisser.manager.domain.util.validation.impl.CPFValidator;

public enum PersonType {

    CNPJ(new CNPJValidator()),
    CPF(new CPFValidator());

     private final Validatable<?> validator;

    <T> PersonType(Validatable<T> validator) {
        this.validator = validator;
    }

    public <T> boolean isValid(T obj) {
        return ((Validatable<T>) validator).test(obj);
    }

    public <T> Validatable<T> getValidator() {
        return ((Validatable<T>) validator);
    }
}
