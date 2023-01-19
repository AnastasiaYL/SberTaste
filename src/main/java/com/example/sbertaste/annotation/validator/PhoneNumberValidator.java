package com.example.sbertaste.annotation.validator;

import com.example.sbertaste.annotation.validation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        {
            return phone != null && phone.matches("\\+7[0-9]{10}");
        }
    }
}
