package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Map;

public class EntityIdValidationValidator implements ConstraintValidator<EntityIdValidation, List<Map<String, Object>>> {

    @Override
    public boolean isValid(List<Map<String, Object>> categories, ConstraintValidatorContext context) {
        return categories != null && categories.stream()
                .allMatch(category -> category != null
                        && category.containsKey("id")
                        && category.get("id") instanceof Integer
                        && (Integer) category.get("id") > 0);
    }
}

