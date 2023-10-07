package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.CategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.CategoryResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.services.implementations.CategoryServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryServiceImplementation categoryServiceImplementation;

    @PostMapping("addCategory")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {

        return new ResponseEntity<>(categoryServiceImplementation.createCategory(categoryRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("showCategories")
    public ResponseEntity<BaseShowAllResponseDTO<CategoryResponseDTO>> showCategories(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }


        return new ResponseEntity<>(categoryServiceImplementation.showCategories(page, pageSize), HttpStatus.OK);
    }

    @GetMapping("showCategoryById/{id}")
    public ResponseEntity<CategoryResponseDTO> showCategoryById(@PathVariable("id") int categoryId) {


        return new ResponseEntity<>(categoryServiceImplementation.showCategoryById(categoryId), HttpStatus.OK);
    }

    @PutMapping("updateCategory/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO, @PathVariable("id") int categoryId) {

        return new ResponseEntity<>(categoryServiceImplementation.updateCategory(categoryRequestDTO, categoryId), HttpStatus.CREATED);
    }
}
