package dev.mohibullah.pizzaappbackendjava.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mohibullah.pizzaappbackendjava.enums.Stage;
import dev.mohibullah.pizzaappbackendjava.models.OrderItem;
import dev.mohibullah.pizzaappbackendjava.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderResponseDTO extends BaseResponseDTO {

    private int id;
    private String deliveryAddress;
    private Double orderPrice;
    private Double deliveryCost;
    private Double discount;
    private Double totalPrice;
    private String paymentMethod;
    private Stage orderStage;
    private String instruction;
    private User createdBy;
    private User updatedBy;
    private List<OrderItem> orderItem = new ArrayList<>();
}
