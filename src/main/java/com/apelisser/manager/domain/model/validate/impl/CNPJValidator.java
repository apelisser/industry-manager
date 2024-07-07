package com.apelisser.manager.domain.model.validate.impl;

import com.apelisser.manager.domain.model.validate.Validatable;

public class CNPJValidator implements Validatable<String> {

    @Override
    public boolean test(String value) {
        // TODO - implementar validacao de CNPJ
        return value != null && value.length() == 14;
    }

}
