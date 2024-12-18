package com.apelisser.manager.domain.util.validation.impl;

import com.apelisser.manager.domain.util.validation.Validatable;

public class CNPJValidator implements Validatable<String> {

    @Override
    public boolean test(String value) {
        // TODO - implementar validacao de CNPJ
        return value != null && value.length() == 14;
    }

}
