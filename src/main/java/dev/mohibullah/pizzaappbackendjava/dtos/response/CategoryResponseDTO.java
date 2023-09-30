package dev.mohibullah.pizzaappbackendjava.dtos.response;

import dev.mohibullah.pizzaappbackendjava.models.Product;
import dev.mohibullah.pizzaappbackendjava.models.SubCategory;
import dev.mohibullah.pizzaappbackendjava.models.Topping;
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
public class CategoryResponseDTO {
    private int id;
    private String name;

    private List<SubCategory> subCategories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Topping> toppings = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//}   private Optional<List<SubCategory>> subCategories;
//    private Optional<List<Product>> products;
//    private Optional<List<Topping>> toppings;
}
