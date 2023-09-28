package dev.mohibullah.pizzaappbackendjava.dtos.request;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomEntityIdValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToppingRequestDTO {
    @NotBlank(message = "Topping name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Topping name must consist only of alphabetic characters.")
    @Size(min = 2, max = 15, message = "Topping name exceeds 15 characters or is less than 2 characters")
    private String name;

    @NotNull(message = "Topping price cannot be null")
    @Positive(message = "Topping price must be a positive number")
    private Double price;

    @NotNull(message = "Categories must be present")
    @Size(min = 1, message = "Empty categories not allowed")
    @CustomEntityIdValidation
    private List<Map<String, Integer>> categories;
}
