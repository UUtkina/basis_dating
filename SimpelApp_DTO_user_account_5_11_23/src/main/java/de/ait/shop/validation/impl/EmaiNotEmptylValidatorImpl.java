package de.ait.shop.validation.impl;

import de.ait.shop.validation.EmailValidator;

public class EmaiNotEmptylValidatorImpl implements EmailValidator {
    @Override
    public void validate(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email ist nicht correct");
        }
    }
}
