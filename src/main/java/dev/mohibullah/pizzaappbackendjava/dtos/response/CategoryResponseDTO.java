package dev.mohibullah.pizzaappbackendjava.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mohibullah.pizzaappbackendjava.models.Product;
import dev.mohibullah.pizzaappbackendjava.models.SubCategory;
import dev.mohibullah.pizzaappbackendjava.models.Topping;
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
public class CategoryResponseDTO extends BaseResponseDTO {
    private int id;
    private String name;
    private List<SubCategory> subCategories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Topping> toppings = new ArrayList<>();
}
