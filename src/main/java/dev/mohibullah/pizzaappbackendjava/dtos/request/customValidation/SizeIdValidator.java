package dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mohibullah.pizzaappbackendjava.dtos.request.ProductRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class SizeIdValidator implements ConstraintValidator<SizeIdConstraint, String> {
    @Override
    public boolean isValid(String sizeId, ConstraintValidatorContext context) {
        try {
            // Deserialize the sizeId JSON string into a List<SizeIdDTO>
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductRequestDTO.SizeIdDTO> sizeIdList = objectMapper.readValue(
                    sizeId,
                    new TypeReference<List<ProductRequestDTO.SizeIdDTO>>() {}
            );

            // Perform validation for each SizeIdDTO in the list
            for (ProductRequestDTO.SizeIdDTO sizeIdDTO : sizeIdList) {
                if (sizeIdDTO.getId() == null || sizeIdDTO.getId() < 1) {
                    return false;
                }
                if (sizeIdDTO.getPrice() == null || sizeIdDTO.getPrice() <= 0) {
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
