package dev.mohibullah.pizzaappbackendjava.dtos.response;

import dev.mohibullah.pizzaappbackendjava.models.Category;
import dev.mohibullah.pizzaappbackendjava.models.Products_Sizes_Prices;
import dev.mohibullah.pizzaappbackendjava.models.SubCategory;
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
public class ProductResponseDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int id;
    private String name;
    private String description;
    private String status;
    private SubCategory subCategory;
    private Category category;
    private List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<>();
}
