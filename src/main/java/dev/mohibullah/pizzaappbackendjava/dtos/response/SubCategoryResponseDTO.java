package dev.mohibullah.pizzaappbackendjava.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mohibullah.pizzaappbackendjava.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SubCategoryResponseDTO extends BaseResponseDTO {
    private int id;
    private String name;
    private Category category;
//    private CategoryResponseDTO category;
}
