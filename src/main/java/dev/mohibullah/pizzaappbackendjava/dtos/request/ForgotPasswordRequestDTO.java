package dev.mohibullah.pizzaappbackendjava.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequestDTO {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    private String email;
}
