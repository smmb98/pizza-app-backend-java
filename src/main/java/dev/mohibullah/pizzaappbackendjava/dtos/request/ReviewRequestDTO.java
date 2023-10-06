package dev.mohibullah.pizzaappbackendjava.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDTO {
    @NotBlank(message = "Review description cannot be blank")
    @Size(min = 2, max = 15, message = "Review description exceeds 15 characters or is less than 2 characters")
    private String description;

    @NotNull(message = "Review rating cannot be null")
    @Positive(message = "Review rating must be a positive number")
    private Double rating;
}
