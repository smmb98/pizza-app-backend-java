package dev.mohibullah.pizzaappbackendjava.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    @NotBlank(message = "Delivery address cannot be blank")
    private String deliveryAddress;

    @NotNull(message = "Order price cannot be null")
    @Positive(message = "Order price must be a positive number")
    private Double orderPrice;

    @NotNull(message = "Delivery cost cannot be null")
    @Positive(message = "Delivery cost must be a positive number")
    private Double deliveryCost;

    @NotNull(message = "Discount cannot be null")
    @PositiveOrZero(message = "Discount must be a non-negative number")
    private Double discount;

    @NotNull(message = "Total price cannot be null")
    @Positive(message = "Total price must be a positive number")
    private Double totalPrice;

    @NotBlank(message = "Payment method cannot be blank")
    @Pattern(regexp = "^(VISA Card|Cash on Delivery)$", message = "Payment method should be 'VISA Card' or 'Cash on Delivery'")
    private String paymentMethod;

    private String instruction;

    @NotNull(message = "Order items must be present")
    @Size(min = 1, message = "Order items array should have at least one element")
    @Valid
    private List<OrderItemRequestDTO> orderItems;

}


