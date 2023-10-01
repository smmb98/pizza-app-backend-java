package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ToppingRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
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
    public ResponseEntity<BaseShowAllResponseDTO<ToppingResponseDTO>> showToppings(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        if (page < 0 || pageSize < 0) {
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


        BaseShowAllResponseDTO<ToppingResponseDTO> baseShowAllResponseDTO = new BaseShowAllResponseDTO<>();
        baseShowAllResponseDTO.setContent(toppingList);
        baseShowAllResponseDTO.setPage(page);
        baseShowAllResponseDTO.setPageSize(pageSize);
        baseShowAllResponseDTO.setTotalPages(1);
        baseShowAllResponseDTO.setTotalElements(toppingList.size());
        baseShowAllResponseDTO.setLast(true);
        return new ResponseEntity<>(baseShowAllResponseDTO, HttpStatus.OK);
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
