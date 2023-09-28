package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ProductRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.request.SubCategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SubCategoryResponseDTO;
import dev.mohibullah.pizzaappbackendjava.models.Category;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @PostMapping("addProduct")
    public ResponseEntity createCategory(@Valid @ModelAttribute ProductRequestDTO productRequestDTO) throws IOException {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        MultipartFile productImage = productRequestDTO.getProductImage();

        // Validate image file type
        if (!Objects.requireNonNull(productImage.getContentType()).startsWith("image/webp")) {
            return ResponseEntity.badRequest().body("File type must be webp!");
        }

        System.out.println(productRequestDTO.getName());



        File file = new File("src/main/resources/static/ProductImages/", productRequestDTO.getName() +".webp");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(productImage.getBytes());
        }



//        SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
//        subCategoryResponseDTO.setId(1);
//        subCategoryResponseDTO.setName(subCategoryRequestDTO.getName());
//
//        Category category = new Category();
//        category.setId(subCategoryRequestDTO.getCategoryId());
//        category.setName("Pizza Something inside subcategory");
//        subCategoryResponseDTO.setCategory(category);
//
//        System.out.println(subCategoryResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");

    }

    @GetMapping("showSubCategories")
    public ResponseEntity<BaseResponseDTO<SubCategoryResponseDTO>> showSubCategories(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<SubCategoryResponseDTO> subCategoryList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
            subCategoryResponseDTO.setId(i + 1);
            subCategoryResponseDTO.setName("Hardcoded SubCategory");
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

        Category category = new Category();
        category.setId(subCategoryRequestDTO.getCategoryId());
        category.setName("Pizza Something inside subcategory");
        subCategoryResponseDTO.setCategory(category);

        System.out.println(subCategoryResponseDTO);
        return new ResponseEntity<>(subCategoryResponseDTO, HttpStatus.CREATED);
    }
}
