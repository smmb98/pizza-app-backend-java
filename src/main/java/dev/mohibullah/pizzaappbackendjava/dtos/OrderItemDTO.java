package dev.mohibullah.pizzaappbackendjava.dtos;

import dev.mohibullah.pizzaappbackendjava.dtos.request.customValidation.EntityIdValidation;
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
public class OrderItemDTO {
    @NotBlank(message = "Invalid product name")
    private String productName;

    @NotNull(message = "Invalid product")
    @Positive(message = "Invalid product")
    private Long product;

    @NotNull(message = "Invalid product price")
    @Positive(message = "Invalid product price")
    private Double productPrice;

    @NotNull(message = "Invalid topping")
    @Size(min = 1, message = "Empty topping not allowed")
    @EntityIdValidation
    private List<Map<String, Integer>> topping;

    @NotNull(message = "Invalid item quantity")
    @Positive(message = "Invalid item quantity")
    private Integer itemQty;

    @NotNull(message = "Invalid size")
    @Positive(message = "Invalid size")
    private Integer size;

    @NotBlank(message = "Invalid size measurement")
    private String sizeMeasurement;

    @NotBlank(message = "Invalid size description")
    private String sizeDescription;

    @NotNull(message = "Invalid sub-total price")
    @Positive(message = "Invalid sub-total price")
    private Double subTotalPrice;

}
