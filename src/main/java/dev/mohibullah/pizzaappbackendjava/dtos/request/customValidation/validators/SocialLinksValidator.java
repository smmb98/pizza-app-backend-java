package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.validators;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomSocialLinks;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class SocialLinksValidator implements ConstraintValidator<CustomSocialLinks, String> {
    @Override
    public void initialize(CustomSocialLinks constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        String[] links = value.split(";");

        if (links.length != 5){
            return false;
        }

        Set<String> validLinkTypes = new HashSet<>();
        validLinkTypes.add("facebook");
        validLinkTypes.add("youtube");
        validLinkTypes.add("twitter");
        validLinkTypes.add("instagram");

        for (String link : links) {
            if (!link.isEmpty() && !isValidSocialLink(link, validLinkTypes)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidSocialLink(String link, Set<String> validLinkTypes) {
        // Check if the link corresponds to one of the valid social link types
        return validLinkTypes.contains(link.toLowerCase());
    }
}