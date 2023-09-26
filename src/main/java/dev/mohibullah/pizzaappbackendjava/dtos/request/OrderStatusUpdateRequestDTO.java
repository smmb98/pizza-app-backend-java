package dev.mohibullah.pizzaappbackendjava.dtos.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateRequestDTO {
    @Pattern(regexp = "^(Cancelled|)$", message = "Wrong order status")
    private String orderStatus;
}
