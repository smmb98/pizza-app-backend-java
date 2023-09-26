package dev.mohibullah.pizzaappbackendjava.dtos.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "First Name can't be empty")
    private String firstName;

    @NotBlank(message = "Last Name can't be empty")
    private String lastName;

    @NotBlank(message = "Mobile Number can't be empty")
    private String mobileNo;

    @NotBlank(message = "Address can't be empty")
    private String address;

    @NotBlank(message = "Please provide your role")
    private String role;

    @Size(min = 8, message = "Password must have at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
            message = "Password must contain at least 1 lowercase, 1 uppercase, 1 number, and 1 special character"
    )
    private String password;
}
