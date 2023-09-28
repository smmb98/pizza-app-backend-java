package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.validators.TimingsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimingsValidator.class)
@Documented
public @interface CustomTimings {
    String message() default "Invalid timings format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}