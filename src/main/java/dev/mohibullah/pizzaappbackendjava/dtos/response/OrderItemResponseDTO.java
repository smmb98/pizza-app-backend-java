package dev.mohibullah.pizzaappbackendjava.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderItemResponseDTO extends BaseResponseDTO {
    private int id;
    private Integer itemQty;
    private Double productPrice;
    private Double subTotalPrice;
    private String sizeMeasurement;
    private String sizeDescription;
    private String productName;
    private List<ToppingResponseDTO> topping = new ArrayList<>();
    private ProductResponseDTO product;
    private SizeResponseDTO size;

}

