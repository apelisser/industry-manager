package com.apelisser.manager.domain.util.validation.impl;

import com.apelisser.manager.domain.util.validation.Validatable;

public class CNPJValidator implements Validatable<String> {

    private static final int[] WEIGHTS = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    @Override
    public boolean test(String value) {
        basicAssertions(value);
        return validate(value);
    }

    private boolean validate(String cnpj) {
        final int firstDigit = calculateDigit(cnpj, 12, 1);
        final int secondDigit = calculateDigit(cnpj, 13, 0);
        
        return Character.getNumericValue(cnpj.charAt(12)) == firstDigit
            && Character.getNumericValue(cnpj.charAt(13)) == secondDigit;
    }

    private int calculateDigit(final String cnpj, final int length, final int offset) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * WEIGHTS[i + offset];
        }
        sum = 11 - (sum % 11);
        return sum > 9 ? 0 : sum;
    }

    private void basicAssertions(final String cnpj) {
        if (cnpj == null || cnpj.isBlank()) {
            throw new IllegalArgumentException("CNPJ must not be null or blank.");
        }

        if (!cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("CNPJ must contain exactly 14 numeric digits.");
        }

        if (cnpj.matches("^(\\d)\\1+$")) {
            throw new IllegalArgumentException("CNPJ cannot contain all identical digits.");
        }
    }

}
