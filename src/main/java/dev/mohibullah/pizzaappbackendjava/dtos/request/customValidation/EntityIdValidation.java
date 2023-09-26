package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EntityIdValidationValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityIdValidation {
    String message() default "Categories must be present, an array, and contain positive integer 'id' values";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
