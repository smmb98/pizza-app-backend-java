package dev.mohibullah.pizzaappbackendjava.dtos.response;

import dev.mohibullah.pizzaappbackendjava.models.Product;
import dev.mohibullah.pizzaappbackendjava.models.SubCategory;
import dev.mohibullah.pizzaappbackendjava.models.Topping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeResponseDTO {
    private int id;
    private String name;

    private Optional<List<SubCategory>> subCategories;
    private Optional<List<Product>> products;
    private Optional<List<Topping>> toppings;
}
