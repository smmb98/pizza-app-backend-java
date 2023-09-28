package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.validators.PhoneNumbersValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumbersValidator.class)
@Documented
public @interface CustomPhoneNumbers {
    String message() default "Invalid phone numbers format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}