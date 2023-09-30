package dev.mohibullah.pizzaappbackendjava.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponseDTO {
    private int id;
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
