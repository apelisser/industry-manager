package com.apelisser.manager.domain.util.validation.impl;

import com.apelisser.manager.domain.util.validation.Validatable;

public class CPFValidator implements Validatable<String> {

    private static final int[] WEIGHTS = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    @Override
    public boolean test(String value) {
        basicAssertions(value);
        return validate(value);
    }

    private boolean validate(final String cpf) {
        int firstDigit = calculateCpfDigit(cpf, 9, 1);
        int secondDigit = calculateCpfDigit(cpf, 10, 0);

        return Character.getNumericValue(cpf.charAt(9)) == firstDigit
            && Character.getNumericValue(cpf.charAt(10)) == secondDigit;
    }

    private int calculateCpfDigit(final String cpf, final int length, final int offset) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * WEIGHTS[i + offset];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private void basicAssertions(final String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF must not be null or blank.");
        }

        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF must contain exactly 11 numeric digits.");
        }

        if (cpf.matches("^(\\d)\\1+$")) {
            throw new IllegalArgumentException("CPF cannot contain all identical digits.");
        }
    }

}
