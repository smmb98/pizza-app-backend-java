package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.validators;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomEntityIdValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Map;

public class EntityIdValidator implements ConstraintValidator<CustomEntityIdValidation, List<Map<String, Integer>>> {

    @Override
    public boolean isValid(List<Map<String, Integer>> categories, ConstraintValidatorContext context) {
        return categories != null && categories.stream()
                .allMatch(category -> category != null
                        && category.containsKey("id")
                        && category.get("id") != null
                        && category.get("id") > 0);
    }
}

