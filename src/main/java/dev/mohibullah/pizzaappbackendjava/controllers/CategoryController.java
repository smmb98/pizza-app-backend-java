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

//        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
//        categoryResponseDTO.setId(1);
//        categoryResponseDTO.setName(categoryRequestDTO.getName());
//        categoryResponseDTO.setCreatedAt(LocalDateTime.now());
//        categoryResponseDTO.setUpdatedAt(LocalDateTime.now());
//        System.out.println(categoryResponseDTO);
//        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);

        return new ResponseEntity<>(categoryServiceImplementation.createCategory(categoryRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("showCategories")
    public ResponseEntity<BaseShowAllResponseDTO<CategoryResponseDTO>> showCategories(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }


//        List<CategoryResponseDTO> categoryList = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
//            categoryResponseDTO.setId(i + 1);
//            categoryResponseDTO.setName("Hardcoded Category");
//            categoryResponseDTO.setCreatedAt(LocalDateTime.now());
//            categoryResponseDTO.setUpdatedAt(LocalDateTime.now());
//            categoryList.add(categoryResponseDTO);
//        }
//        System.out.println(categoryList);
//
//
//        BaseShowAllResponseDTO<CategoryResponseDTO> baseShowAllResponseDTO = new BaseShowAllResponseDTO<>();
//        baseShowAllResponseDTO.setContent(categoryList);
//        baseShowAllResponseDTO.setPage(page);
//        baseShowAllResponseDTO.setPageSize(pageSize);
//        baseShowAllResponseDTO.setTotalPages(1);
//        baseShowAllResponseDTO.setTotalElements(categoryList.size());
//        baseShowAllResponseDTO.setLast(true);
//        return new ResponseEntity<>(baseShowAllResponseDTO, HttpStatus.OK);

        return new ResponseEntity<>(categoryServiceImplementation.showCategories(page, pageSize), HttpStatus.OK);
    }

    @GetMapping("showCategoryById/{id}")
    public ResponseEntity<CategoryResponseDTO> showCategoryById(@PathVariable("id") int categoryId) {
//        System.out.println(categoryId);
//        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
//        categoryResponseDTO.setId(categoryId);
//        categoryResponseDTO.setName("Pizza Something");
//        categoryResponseDTO.setCreatedAt(LocalDateTime.now());
//        categoryResponseDTO.setUpdatedAt(LocalDateTime.now());
//        System.out.println(categoryResponseDTO);
//        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);

        return new ResponseEntity<>(categoryServiceImplementation.showCategoryById(categoryId), HttpStatus.OK);
    }

    @PutMapping("updateCategory/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO, @PathVariable("id") int categoryId) {
//        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
//        categoryResponseDTO.setId(categoryId);
//        categoryResponseDTO.setName(categoryRequestDTO.getName());
//        System.out.println(categoryResponseDTO);
//        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
        return new ResponseEntity<>(categoryServiceImplementation.updateCategory(categoryRequestDTO, categoryId), HttpStatus.CREATED);
    }
}
