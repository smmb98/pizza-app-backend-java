package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.validators;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomPhoneNumbers;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumbersValidator implements ConstraintValidator<CustomPhoneNumbers, String> {
    @Override
    public void initialize(CustomPhoneNumbers constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        String[] phoneNumbers = value.split(";");

        for (String phoneNumber : phoneNumbers) {
            if (isValidPhoneNumber(phoneNumber)) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {

        return phoneNumber.matches("\\d{7}"); //  7-digit phone numbers
    }
}