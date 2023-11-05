package de.ait.shop.validation.impl;

import de.ait.shop.validation.PasswordValidator;

public class PasswordNotEmptyValidatorImpl implements PasswordValidator {
    @Override
    public void validate(String password) {
        if (password == null || password.equals("") || password.equals(" ")) {//.isBlank
            throw new IllegalArgumentException("Password ist nicht correct");

        }
    }
}
