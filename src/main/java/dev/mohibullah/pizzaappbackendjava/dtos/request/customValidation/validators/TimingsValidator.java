package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.validators;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomTimings;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimingsValidator implements ConstraintValidator<CustomTimings, String> {
    @Override
    public void initialize(CustomTimings constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        String[] timings = value.split(";");

        return timings.length == 2 && isValidTime(timings[0]) && isValidTime(timings[1]);
    }

    private boolean isValidTime(String time) {
        return time.matches("^([0-1]\\d|2[0-3]):[0-5]\\d$"); // Example: HH:mm format
    }
}