package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.UpdateUserRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.UserResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidUserRoleParamException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("fetchUsers")
    public ResponseEntity<BaseResponseDTO<UserResponseDTO>> showUsers(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                      @RequestParam(value = "role", defaultValue = "customer") String role) {
        if (pageNo <= 0 || pageSize <= 0) {
            throw new InvalidPaginationException();
        }

        if (!role.equals("customer") && !role.equals("admin")) {
            throw new InvalidUserRoleParamException();
        }

        List<UserResponseDTO> userList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(i + 1);
            userResponseDTO.setEmail("abc@example.com");
            userResponseDTO.setPassword("abc12323");
            userResponseDTO.setAddress("localhost");
            userResponseDTO.setFirstName("John");
            userResponseDTO.setLastName("Doe");
            userResponseDTO.setMobileNo("1234567");
            userResponseDTO.setRole("customer");
//            userResponseDTO.setStripeId("1234567");
            userResponseDTO.setCreatedAt(LocalDateTime.now());
            userResponseDTO.setUpdatedAt(LocalDateTime.now());
            userList.add(userResponseDTO);
        }
        System.out.println(userList);


        BaseResponseDTO<UserResponseDTO> baseResponseDTO = new BaseResponseDTO<>();
        baseResponseDTO.setContent(userList);
        baseResponseDTO.setPageNo(pageNo);
        baseResponseDTO.setPageSize(pageSize);
        baseResponseDTO.setTotalPages(1);
        baseResponseDTO.setTotalElements(userList.size());
        baseResponseDTO.setLast(true);
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }

    @PutMapping("updateUserInfo/{id}")
    public ResponseEntity<UserResponseDTO> updateUserInfo(@Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO, @PathVariable("id") int userId) {

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userId);
        userResponseDTO.setEmail("user@example.com");
        userResponseDTO.setPassword(updateUserRequestDTO.getPassword());
        userResponseDTO.setAddress(updateUserRequestDTO.getAddress());
        userResponseDTO.setFirstName(updateUserRequestDTO.getFirstName());
        userResponseDTO.setLastName(updateUserRequestDTO.getLastName());
        userResponseDTO.setMobileNo(updateUserRequestDTO.getMobileNo());
        userResponseDTO.setRole("customer");
        userResponseDTO.setCreatedAt(LocalDateTime.now());
        userResponseDTO.setUpdatedAt(LocalDateTime.now());

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }
}
