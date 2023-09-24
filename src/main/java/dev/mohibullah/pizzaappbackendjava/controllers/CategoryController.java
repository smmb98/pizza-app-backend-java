package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.CategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.CategoryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @PostMapping("addCategory")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);


        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(1);
        categoryResponseDTO.setName(categoryRequestDTO.getName());
        System.out.println(categoryResponseDTO);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
    }
}
