package dev.mohibullah.pizzaappbackendjava.dtos.request;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.SizeIdConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotNull(message = "Product image is required")
    private MultipartFile productImage;

    @NotEmpty(message = "Product name required")
    private String name;

    @NotEmpty(message = "Description required")
    private String description;

    @NotEmpty(message = "Image name required")
    private String imageName;

    @SizeIdConstraint // Custom validator for sizeId
    private String sizeId;
    @NotNull(message = "Category ID required")
    @Min(value = 1, message = "Category ID must be greater than or equal to 1")
    private Long categoryId;

    @Min(value = 1, message = "SubCategory ID must be greater than or equal to 1")
    private Long subCategoryId;

    @NotEmpty(message = "Status required")
    private String status;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SizeIdDTO {
        @NotNull(message = "Size ID is required")
        @Min(value = 1, message = "Size ID must be greater than or equal to 1")
        private Long id;

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        private Double price;
    }
}
