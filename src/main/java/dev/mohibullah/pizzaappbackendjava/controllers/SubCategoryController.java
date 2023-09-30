package dev.mohibullah.pizzaappbackendjava.controllers;


import dev.mohibullah.pizzaappbackendjava.dtos.request.SubCategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SubCategoryResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.models.Category;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/subCategory")
public class SubCategoryController {
    @PostMapping("addSubCategory")
    public ResponseEntity<SubCategoryResponseDTO> createCategory(@Valid @RequestBody SubCategoryRequestDTO subCategoryRequestDTO) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
        subCategoryResponseDTO.setId(1);
        subCategoryResponseDTO.setName(subCategoryRequestDTO.getName());
        subCategoryResponseDTO.setCreatedAt(LocalDateTime.now());
        subCategoryResponseDTO.setUpdatedAt(LocalDateTime.now());
        Category category = new Category();
        category.setId(subCategoryRequestDTO.getCategoryId());
        category.setName("Pizza Something inside subcategory");
        subCategoryResponseDTO.setCategory(category);

        System.out.println(subCategoryResponseDTO);
        return new ResponseEntity<>(subCategoryResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("showSubCategories")
    public ResponseEntity<BaseResponseDTO<SubCategoryResponseDTO>> showSubCategories(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        if (pageNo <= 0 || pageSize <= 0) {
            throw new InvalidPaginationException();
        }

        List<SubCategoryResponseDTO> subCategoryList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
            subCategoryResponseDTO.setId(i + 1);
            subCategoryResponseDTO.setName("Hardcoded SubCategory");
            subCategoryResponseDTO.setCreatedAt(LocalDateTime.now());
            subCategoryResponseDTO.setUpdatedAt(LocalDateTime.now());
            Category category = new Category();
            category.setId(i + 1);
            category.setName("Pizza Something inside subcategory");
            subCategoryResponseDTO.setCategory(category);

            subCategoryList.add(subCategoryResponseDTO);
        }
        System.out.println(subCategoryList);


        BaseResponseDTO<SubCategoryResponseDTO> baseResponseDTO = new BaseResponseDTO<>();
        baseResponseDTO.setContent(subCategoryList);
        baseResponseDTO.setPageNo(pageNo);
        baseResponseDTO.setPageSize(pageSize);
        baseResponseDTO.setTotalPages(1);
        baseResponseDTO.setTotalElements(subCategoryList.size());
        baseResponseDTO.setLast(true);
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }

    @PutMapping("updateSubCategory/{id}")
    public ResponseEntity<SubCategoryResponseDTO> updateSubCategory(@Valid @RequestBody SubCategoryRequestDTO subCategoryRequestDTO, @PathVariable("id") int subCategoryId) {
        SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
        subCategoryResponseDTO.setId(subCategoryId);
        subCategoryResponseDTO.setName(subCategoryRequestDTO.getName());
        subCategoryResponseDTO.setCreatedAt(LocalDateTime.now());
        subCategoryResponseDTO.setUpdatedAt(LocalDateTime.now());
        Category category = new Category();
        category.setId(subCategoryRequestDTO.getCategoryId());
        category.setName("Pizza Something inside subcategory");
        subCategoryResponseDTO.setCategory(category);

        System.out.println(subCategoryResponseDTO);
        return new ResponseEntity<>(subCategoryResponseDTO, HttpStatus.CREATED);
    }

}
