package dev.mohibullah.pizzaappbackendjava.dtos.response;

import dev.mohibullah.pizzaappbackendjava.models.OrderItem;
import dev.mohibullah.pizzaappbackendjava.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int id;
    private String deliveryAddress;
    private Double orderPrice;
    private Double deliveryCost;
    private Double discount;
    private Double totalPrice;
    private String paymentMethod;
    private String orderStage;
    private String instruction;
    private User createdBy;
    private User updatedBy;
    private List<OrderItem> orderItem = new ArrayList<>();
}
