package com.bragado.userregistration.components;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserIdValidator implements ConstraintValidator<UserId, Long> {
    @Override
    public boolean isValid(Long id, ConstraintValidatorContext ctx) {
        Pattern pattern =
                Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(id.toString());
        try {
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }
}