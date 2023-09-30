package dev.mohibullah.pizzaappbackendjava.dtos.request;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.annotations.CustomEntityIdValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    private String name;

    @NotNull(message = "Topping price cannot be null")
    @Positive(message = "Topping price must be a positive number")
    private Double price;

    @NotNull(message = "Categories must be present")
    @Size(min = 1, message = "Empty categories not allowed")
    @CustomEntityIdValidation(message = "Categories must be present, an array, and contain positive integer 'id' values")
    private List<Map<String, Integer>> categories;
}
