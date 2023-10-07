package dev.mohibullah.pizzaappbackendjava.controllers;


import dev.mohibullah.pizzaappbackendjava.dtos.request.SubCategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SubCategoryResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.services.implementations.SubCategoryServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subCategory")
public class SubCategoryController {

    private final SubCategoryServiceImplementation subCategoryServiceImplementation;


    @PostMapping("addSubCategory")
    public ResponseEntity<SubCategoryResponseDTO> createCategory(
            @Valid @RequestBody SubCategoryRequestDTO subCategoryRequestDTO) {

        return new ResponseEntity<>(
                subCategoryServiceImplementation.createSubCategory(subCategoryRequestDTO),
                HttpStatus.CREATED);
    }

    @GetMapping("showSubCategories")
    public ResponseEntity<BaseShowAllResponseDTO<SubCategoryResponseDTO>> showSubCategories(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }

        return new ResponseEntity<>(
                subCategoryServiceImplementation.showSubCategories(page, pageSize),
                HttpStatus.OK);

    }

    @PutMapping("updateSubCategory/{id}")
    public ResponseEntity<SubCategoryResponseDTO> updateSubCategory(
            @Valid @RequestBody SubCategoryRequestDTO subCategoryRequestDTO,
            @PathVariable("id") int subCategoryId) {

        return new ResponseEntity<>(
                subCategoryServiceImplementation.updateSubCategory(subCategoryRequestDTO, subCategoryId),
                HttpStatus.CREATED);
    }

}
