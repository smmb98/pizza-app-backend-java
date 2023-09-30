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
public class OrderItemRequestDTO {
    @NotBlank(message = "Invalid product name in order item")
    private String productName;

    @NotNull(message = "Invalid product in order item")
    @Positive(message = "Product Id must be positive integer in order item")
    private Integer product;

    @NotNull(message = "Invalid product price in order item")
    @Positive(message = "Product price must be positive integer in order item")
    private Double productPrice;

    @NotNull(message = "Invalid topping in order item")
    @Size(min = 1, message = "Empty topping not allowed in order item")
    @CustomEntityIdValidation(message = "Toppings must be present, an array, and contain positive integer 'id' values")
    private List<Map<String, Integer>> topping;

    @NotNull(message = "Invalid item quantity in order item")
    @Positive(message = "Item quantity in order item")
    private Integer itemQty;

    @NotNull(message = "Invalid size in order item")
    @Positive(message = "Size Id must be positive integer in order item")
    private Integer size;

    @NotBlank(message = "Invalid size measurement in order item")
    private String sizeMeasurement;

    @NotBlank(message = "Invalid size description in order item")
    private String sizeDescription;

    @NotNull(message = "Invalid sub-total price in order item")
    @Positive(message = "Sub-total price must be positive integer in order item")
    private Double subTotalPrice;
}

