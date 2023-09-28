package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.validators.SizeIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SizeIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomSizeIdConstraint {
    String message() default "Invalid sizeId format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
