package com.apelisser.manager.domain.model.validate.impl;

import com.apelisser.manager.domain.model.validate.Validatable;

public class CPFValidator implements Validatable<String> {

    @Override
    public boolean test(String value) {
        // TODO - implementar validacao de cpf
        return value != null && value.length() == 11;
    }

}
