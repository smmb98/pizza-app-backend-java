package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.CategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.CategoryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @PostMapping("addCategory")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(1);
        categoryResponseDTO.setName(categoryRequestDTO.getName());
        categoryResponseDTO.setCreatedAt(LocalDateTime.now());
        categoryResponseDTO.setUpdatedAt(LocalDateTime.now());
        System.out.println(categoryResponseDTO);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("showCategories")
    public ResponseEntity<BaseResponseDTO<CategoryResponseDTO>> showCategories(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<CategoryResponseDTO> categoryList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(i + 1);
            categoryResponseDTO.setName("Hardcoded Category");
            categoryResponseDTO.setCreatedAt(LocalDateTime.now());
            categoryResponseDTO.setUpdatedAt(LocalDateTime.now());
            categoryList.add(categoryResponseDTO);
        }
        System.out.println(categoryList);



        BaseResponseDTO<CategoryResponseDTO> baseResponseDTO = new BaseResponseDTO<>();
        baseResponseDTO.setContent(categoryList);
        baseResponseDTO.setPageNo(pageNo);
        baseResponseDTO.setPageSize(pageSize);
        baseResponseDTO.setTotalPages(1);
        baseResponseDTO.setTotalElements(categoryList.size());
        baseResponseDTO.setLast(true);
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }

    @GetMapping("showCategoryById/{id}")
    public ResponseEntity<CategoryResponseDTO> showCategoryById(@PathVariable("id") int categoryId) {
        System.out.println(categoryId);
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(categoryId);
        categoryResponseDTO.setName("Pizza Something");
        categoryResponseDTO.setCreatedAt(LocalDateTime.now());
        categoryResponseDTO.setUpdatedAt(LocalDateTime.now());
        System.out.println(categoryResponseDTO);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @PutMapping("updateCategory/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO, @PathVariable("id") int categoryId) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(categoryId);
        categoryResponseDTO.setName(categoryRequestDTO.getName());
        System.out.println(categoryResponseDTO);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
    }
}
