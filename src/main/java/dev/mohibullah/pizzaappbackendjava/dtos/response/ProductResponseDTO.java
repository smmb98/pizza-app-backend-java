package dev.mohibullah.pizzaappbackendjava.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mohibullah.pizzaappbackendjava.enums.Status;
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
public class ProductResponseDTO extends BaseResponseDTO {

    private int id;
    private String name;
    private String imageName;
    private String description;
    private Status status;
    private SubCategoryResponseDTO subCategory;
    private CategoryResponseDTO category;
    private List<Products_Sizes_PricesResponseDTO> productsSizesPrices = new ArrayList<>();

    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Products_Sizes_PricesResponseDTO extends BaseResponseDTO {
        private int id;
        private SizeResponseDTO size;
        private double price;

    }
}