package dev.mohibullah.pizzaappbackendjava.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SizeRequestDTO {
    @NotBlank(message = "Size measurement cannot be blank")
    private String measurement;

    @NotBlank(message = "Size description cannot be blank")
    private String description;


}
