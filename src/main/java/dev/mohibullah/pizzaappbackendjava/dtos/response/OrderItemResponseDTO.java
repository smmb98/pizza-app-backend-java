package dev.mohibullah.pizzaappbackendjava.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mohibullah.pizzaappbackendjava.models.Product;
import dev.mohibullah.pizzaappbackendjava.models.Size;
import dev.mohibullah.pizzaappbackendjava.models.Topping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderItemResponseDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int id;
    private Integer itemQty;
    private Double productPrice;
    private Double subTotalPrice;
    private String sizeMeasurement;
    private String sizeDescription;
    private String productName;
    private List<Topping> topping = new ArrayList<>();
    private Product product;
    private Size size;

}

