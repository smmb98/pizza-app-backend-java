package dev.mohibullah.pizzaappbackendjava.dtos.request;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomImage;
import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomSizeIdConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotNull(message = "Product image is required")
    @CustomImage(message = "ProductImage must be of type webp")
    private MultipartFile productImage;

    @NotEmpty(message = "Product name required")
    private String name;

    @NotEmpty(message = "Description required")
    private String description;

    @CustomSizeIdConstraint // Custom validator for sizeId
    private String sizeId;
    @NotNull(message = "Category ID required")
    @Pattern(regexp = "^[1-9]\\d*$", message = "Invalid CategoryId. Please enter a positive integer.")
    private String categoryId;
    @Pattern(regexp = "^[1-9]\\d*$", message = "Invalid SubCategoryId. Please enter a positive integer.")
    private String subCategoryId;

    @NotEmpty(message = "Status required")
    @Pattern(regexp = "^(Active|InActive)$", message = "Wrong product status")
    private String status;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SizeIdDTO {
        @NotNull(message = "Size ID is required")
        @Min(value = 1, message = "Size ID must be greater than or equal to 1")
        private Integer id;

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        private Double price;
    }
}
