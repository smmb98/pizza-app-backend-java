package dev.mohibullah.pizzaappbackendjava.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String mobileNo;
    private String address;
    private String stripeId;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
