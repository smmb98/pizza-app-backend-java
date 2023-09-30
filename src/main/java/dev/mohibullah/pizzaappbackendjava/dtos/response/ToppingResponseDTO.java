package dev.mohibullah.pizzaappbackendjava.dtos.response;

import dev.mohibullah.pizzaappbackendjava.models.Category;
import dev.mohibullah.pizzaappbackendjava.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToppingResponseDTO {
    private String name;
    private Double price;
    private List<Category> categories;
    private User createdBy;
    private User updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int id;
}
