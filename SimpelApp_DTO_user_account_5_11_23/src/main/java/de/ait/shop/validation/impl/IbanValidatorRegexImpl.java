package de.ait.shop.validation.impl;


import de.ait.shop.validation.IbanValidator;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class IbanValidatorRegexImpl implements IbanValidator {

    @Override
    public boolean isValidIban(String iban) {
        if (iban == null || iban.length() < 15 || iban.length() > 34) {
            return false;
        }

        iban = iban.toUpperCase();
        iban = iban.substring(4) + iban.substring(0, 4);
        StringBuilder numericIban = new StringBuilder();

        for (int i = 0; i < iban.length(); i++) {
            numericIban.append(Character.digit(iban.charAt(i), 36));
        }

        BigInteger bi = new BigInteger(numericIban.toString());
        BigInteger modulus = new BigInteger("97");

        return bi.mod(modulus).intValue() == 1;
    }
}
