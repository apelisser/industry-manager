package com.apelisser.manager.domain.enums;

import com.apelisser.manager.domain.model.validate.Validatable;
import com.apelisser.manager.domain.model.validate.impl.CNPJValidator;
import com.apelisser.manager.domain.model.validate.impl.CPFValidator;

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
