package dev.mohibullah.pizzaappbackendjava.dtos.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryRequestDTO {
    @NotBlank(message = "SubCategory name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "SubCategory name must consist only of alphabetic characters.")
    @Size(min = 2, max = 30, message = "SubCategory name exceeds 30 characters or is less than 2 characters")
    private String name;

    @NotNull(message = "categoryId cannot be null")
    @Positive(message = "categoryId must be a positive integer")
    private int categoryId;
}
