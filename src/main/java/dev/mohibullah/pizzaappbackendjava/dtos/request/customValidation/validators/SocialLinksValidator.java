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

        if (links.length != 5) {
            return false;
        }

        Set<String> validLinkTypes = new HashSet<>();
        validLinkTypes.add("facebook");
        validLinkTypes.add("youtube");
        validLinkTypes.add("twitter");
        validLinkTypes.add("instagram");

        for (int i = 0; i < links.length - 1; i++) {
            String link = links[i];
            if (!link.isEmpty() && !isValidSocialLink(link, validLinkTypes)) {
                System.out.println("link is not empty and social link is not valid");
                return false;
            }
        }

        return true;
    }

    private boolean isValidSocialLink(String link, Set<String> validLinkTypes) {
        for (String linkType : validLinkTypes) {
            if (link.contains(linkType)) {
                return true;
            }
        }
        return false;
    }

}