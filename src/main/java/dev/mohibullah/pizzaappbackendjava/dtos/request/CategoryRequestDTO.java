package dev.mohibullah.pizzaappbackendjava.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {
    @NotBlank(message = "Category name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Category name must consist only of alphabetic characters.")
    @Size(min = 2, max = 30, message = "Category name exceeds 30 characters or is less than 2 characters")
    private String name;
}
