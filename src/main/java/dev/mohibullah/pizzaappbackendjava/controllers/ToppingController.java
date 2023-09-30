package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ToppingRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ToppingResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.models.Category;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/topping")
public class ToppingController {
    @PostMapping("addTopping")
    public ResponseEntity<ToppingResponseDTO> createSize(@Valid @RequestBody ToppingRequestDTO toppingRequestDTO) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        List<Category> categoryList = new ArrayList<>();
        for (Map<String, Integer> categoryId : toppingRequestDTO.getCategories()) {
            Category category = new Category();
            category.setId(categoryId.get("id"));
            category.setName("Pizza");
            categoryList.add(category);
        }

        ToppingResponseDTO toppingResponseDTO = new ToppingResponseDTO();
        toppingResponseDTO.setId(1);
        toppingResponseDTO.setName(toppingRequestDTO.getName());
        toppingResponseDTO.setPrice(toppingRequestDTO.getPrice());
        toppingResponseDTO.setCategories(categoryList);
        toppingResponseDTO.setCreatedAt(LocalDateTime.now());
        toppingResponseDTO.setUpdatedAt(LocalDateTime.now());
        System.out.println(toppingResponseDTO);
        return new ResponseEntity<>(toppingResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("showToppings")
    public ResponseEntity<BaseResponseDTO<ToppingResponseDTO>> showToppings(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        if (pageNo <= 0 || pageSize <= 0) {
            throw new InvalidPaginationException();
        }

        List<ToppingResponseDTO> toppingList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            List<Category> categoryList = new ArrayList<>();
//            List<Map<String, Integer>> categories = List.of(
//                    Map.of("id", 1),
//                    Map.of("id", 2),
//                    Map.of("id", 3)
//            );
            for (int j = 0; j < 3; j++) {
                Category category = new Category();
                category.setId(i + 1);
                category.setName("Pizza");
                categoryList.add(category);
            }

            ToppingResponseDTO toppingResponseDTO = new ToppingResponseDTO();
            toppingResponseDTO.setId(1);
            toppingResponseDTO.setName("Fresh Jalapeno Marinara Sauce");
            toppingResponseDTO.setCategories(categoryList);
            toppingResponseDTO.setCreatedAt(LocalDateTime.now());
            toppingResponseDTO.setUpdatedAt(LocalDateTime.now());
            toppingList.add(toppingResponseDTO);
        }
        System.out.println(toppingList);


        BaseResponseDTO<ToppingResponseDTO> baseResponseDTO = new BaseResponseDTO<>();
        baseResponseDTO.setContent(toppingList);
        baseResponseDTO.setPageNo(pageNo);
        baseResponseDTO.setPageSize(pageSize);
        baseResponseDTO.setTotalPages(1);
        baseResponseDTO.setTotalElements(toppingList.size());
        baseResponseDTO.setLast(true);
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }


    @PutMapping("updateTopping/{id}")
    public ResponseEntity<ToppingResponseDTO> updateTopping(@Valid @RequestBody ToppingRequestDTO toppingRequestDTO, @PathVariable("id") int toppingId) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        List<Category> categoryList = new ArrayList<>();
        for (Map<String, Integer> categoryId : toppingRequestDTO.getCategories()) {
            Category category = new Category();
            category.setId(categoryId.get("id"));
            category.setName("Pizza");
            categoryList.add(category);
        }

        ToppingResponseDTO toppingResponseDTO = new ToppingResponseDTO();
        toppingResponseDTO.setId(toppingId);
        toppingResponseDTO.setName(toppingRequestDTO.getName());
        toppingResponseDTO.setPrice(toppingRequestDTO.getPrice());
        toppingResponseDTO.setCategories(categoryList);
        toppingResponseDTO.setCreatedAt(LocalDateTime.now());
        toppingResponseDTO.setUpdatedAt(LocalDateTime.now());
        System.out.println(toppingResponseDTO);
        return new ResponseEntity<>(toppingResponseDTO, HttpStatus.CREATED);
    }

}
