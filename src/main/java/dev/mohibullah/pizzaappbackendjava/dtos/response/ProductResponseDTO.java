package dev.mohibullah.pizzaappbackendjava.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mohibullah.pizzaappbackendjava.enums.Status;
import dev.mohibullah.pizzaappbackendjava.models.Category;
import dev.mohibullah.pizzaappbackendjava.models.Products_Sizes_Prices;
import dev.mohibullah.pizzaappbackendjava.models.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private SubCategory subCategory;
    private Category category;
    private List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<>();
}