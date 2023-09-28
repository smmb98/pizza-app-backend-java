package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.validators;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomImage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ImageValidator implements ConstraintValidator<CustomImage, MultipartFile> {
    @Override
    public void initialize(CustomImage constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        String fileType = file.getOriginalFilename();
        return fileType == null || fileType.toLowerCase().endsWith(".webp");
    }
}