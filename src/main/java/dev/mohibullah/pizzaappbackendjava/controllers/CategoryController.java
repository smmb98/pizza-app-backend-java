package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.CategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.CategoryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @PostMapping("addCategory")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(1);
        categoryResponseDTO.setName(categoryRequestDTO.getName());
        System.out.println(categoryResponseDTO);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("showCategories")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseResponseDTO<CategoryResponseDTO>> showCategories(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<CategoryResponseDTO> categoryList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(i + 1);
            categoryResponseDTO.setName("Hardcoded Category");
            categoryList.add(categoryResponseDTO);
        }
        System.out.println(categoryList);



        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        baseResponseDTO.setContent(categoryList);
        baseResponseDTO.setPageNo(pageNo);
        baseResponseDTO.setPageSize(pageSize);
        baseResponseDTO.setTotalPages(1);
        baseResponseDTO.setLast(true);
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }
}
