package de.ait.shop.validation.impl;

import de.ait.shop.validation.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorRegexImpl implements EmailValidator {
    private  static  final  String REGEX = "^(?=.{1,256}$)[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]" +
            "+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9]" +
            "(?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z]{2,}$";

    @Override
    public void validate(String email) {
        Pattern pattern = Pattern.compile(REGEX);//создаем шаблон по регулярному віражению
        Matcher matcher = pattern.matcher(email);//создаем обїект, которій проверяет віражение
        if (!matcher.matches()){
            throw  new IllegalArgumentException("Email не соответствует формату");
        }
    }
}
