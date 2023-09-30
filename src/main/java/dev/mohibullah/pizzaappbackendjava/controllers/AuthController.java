package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SignInRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.request.SignUpRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SignInResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.UserResponseDTO;
import dev.mohibullah.pizzaappbackendjava.models.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("signin")
    public ResponseEntity<SignInResponseDTO> signIn(@Valid @RequestBody SignInRequestDTO signInRequestDTO) {

        SignInResponseDTO signInResponseDTO = new SignInResponseDTO();
        signInResponseDTO.setToken("JWT token");
        User user = new User();
        user.setId(1);
        user.setEmail(signInRequestDTO.getEmail());
        user.setPassword(signInRequestDTO.getPassword());
        user.setAddress("localhost");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setMobileNo("1234567");
        user.setRole("admin");
//        user.setStripeId("1234567");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        signInResponseDTO.setUser(user);

        return new ResponseEntity<>(signInResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("signup")
    public ResponseEntity<UserResponseDTO> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1);
        userResponseDTO.setEmail(signUpRequestDTO.getEmail());
        userResponseDTO.setPassword(signUpRequestDTO.getPassword());
        userResponseDTO.setAddress(signUpRequestDTO.getAddress());
        userResponseDTO.setFirstName(signUpRequestDTO.getFirstName());
        userResponseDTO.setLastName(signUpRequestDTO.getLastName());
        userResponseDTO.setMobileNo(signUpRequestDTO.getMobileNo());
        userResponseDTO.setRole(signUpRequestDTO.getRole());
//        if (Objects.equals(signUpRequestDTO.getRole(), "customer")) {
//            userResponseDTO.setStripeId("1234567");
//        }
        userResponseDTO.setCreatedAt(LocalDateTime.now());
        userResponseDTO.setUpdatedAt(LocalDateTime.now());

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }
}
