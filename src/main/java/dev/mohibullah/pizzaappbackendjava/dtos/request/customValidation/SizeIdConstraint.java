package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SizeIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SizeIdConstraint {
    String message() default "Invalid sizeId format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
