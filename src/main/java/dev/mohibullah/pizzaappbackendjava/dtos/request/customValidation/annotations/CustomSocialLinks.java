package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.validators.SocialLinksValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SocialLinksValidator.class)
@Documented
public @interface CustomSocialLinks {
    String message() default "Invalid social links format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}