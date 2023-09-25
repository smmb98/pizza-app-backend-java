package dev.mohibullah.pizzaappbackendjava.dtos.response;

import dev.mohibullah.pizzaappbackendjava.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryResponseDTO {
    private int id;
    private String name;
    private Category category;

}
