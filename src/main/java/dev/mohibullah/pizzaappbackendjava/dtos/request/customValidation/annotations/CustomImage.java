package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.validators.ImageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageValidator.class)
@Documented
public @interface CustomImage {
    String message() default "Invalid image format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}