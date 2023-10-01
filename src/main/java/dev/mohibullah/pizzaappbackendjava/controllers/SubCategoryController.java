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
    public ResponseEntity<SubCategoryResponseDTO> createCategory(@Valid @RequestBody SubCategoryRequestDTO subCategoryRequestDTO) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);
//
//        SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
//        subCategoryResponseDTO.setId(1);
//        subCategoryResponseDTO.setName(subCategoryRequestDTO.getName());
//        subCategoryResponseDTO.setCreatedAt(LocalDateTime.now());
//        subCategoryResponseDTO.setUpdatedAt(LocalDateTime.now());
//        Category category = new Category();
//        category.setId(subCategoryRequestDTO.getCategoryId());
//        category.setName("Pizza Something inside subcategory");
//        subCategoryResponseDTO.setCategory(category);
//
//        System.out.println(subCategoryResponseDTO);
//        return new ResponseEntity<>(subCategoryResponseDTO, HttpStatus.CREATED);
        return new ResponseEntity<>(subCategoryServiceImplementation.createSubCategory(subCategoryRequestDTO), HttpStatus.CREATED);

    }

    @GetMapping("showSubCategories")
    public ResponseEntity<BaseShowAllResponseDTO<SubCategoryResponseDTO>> showSubCategories(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }

//        List<SubCategoryResponseDTO> subCategoryList = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//            SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
//            subCategoryResponseDTO.setId(i + 1);
//            subCategoryResponseDTO.setName("Hardcoded SubCategory");
//            subCategoryResponseDTO.setCreatedAt(LocalDateTime.now());
//            subCategoryResponseDTO.setUpdatedAt(LocalDateTime.now());
//            Category category = new Category();
//            category.setId(i + 1);
//            category.setName("Pizza Something inside subcategory");
//            subCategoryResponseDTO.setCategory(category);
//
//            subCategoryList.add(subCategoryResponseDTO);
//        }
//        System.out.println(subCategoryList);
//
//
//        BaseShowAllResponseDTO<SubCategoryResponseDTO> baseShowAllResponseDTO = new BaseShowAllResponseDTO<>();
//        baseShowAllResponseDTO.setContent(subCategoryList);
//        baseShowAllResponseDTO.setPage(page);
//        baseShowAllResponseDTO.setPageSize(pageSize);
//        baseShowAllResponseDTO.setTotalPages(1);
//        baseShowAllResponseDTO.setTotalElements(subCategoryList.size());
//        baseShowAllResponseDTO.setLast(true);
//        return new ResponseEntity<>(baseShowAllResponseDTO, HttpStatus.OK);

        return new ResponseEntity<>(subCategoryServiceImplementation.showSubCategories(page, pageSize), HttpStatus.OK);

    }

    @PutMapping("updateSubCategory/{id}")
    public ResponseEntity<SubCategoryResponseDTO> updateSubCategory(@Valid @RequestBody SubCategoryRequestDTO subCategoryRequestDTO, @PathVariable("id") int subCategoryId) {
//        SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
//        subCategoryResponseDTO.setId(subCategoryId);
//        subCategoryResponseDTO.setName(subCategoryRequestDTO.getName());
//        subCategoryResponseDTO.setCreatedAt(LocalDateTime.now());
//        subCategoryResponseDTO.setUpdatedAt(LocalDateTime.now());
//        Category category = new Category();
//        category.setId(subCategoryRequestDTO.getCategoryId());
//        category.setName("Pizza Something inside subcategory");
//        subCategoryResponseDTO.setCategory(category);
//
//        System.out.println(subCategoryResponseDTO);
//        return new ResponseEntity<>(subCategoryResponseDTO, HttpStatus.CREATED);

        return new ResponseEntity<>(subCategoryServiceImplementation.updateSubCategory(subCategoryRequestDTO, subCategoryId), HttpStatus.CREATED);

    }

}
