package dev.mohibullah.pizzaappbackendjava.dtos.response;

import dev.mohibullah.pizzaappbackendjava.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    private String description;
    private Double rating;
    private User createdBy;
    private User updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int id;
}
