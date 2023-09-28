package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SettingRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SettingController {
    @PutMapping("updateSettings/{id}")
    public ResponseEntity createCategory(@PathVariable("id") int settingId, @RequestParam(value = "setting", defaultValue = "restaurant_info") int pageNo, @Valid @ModelAttribute SettingRequestDTO settingRequestDTO) throws IOException {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        MultipartFile LOGO = settingRequestDTO.getRestaurant_LOGO();
        MultipartFile splashScreen = settingRequestDTO.getRestaurant_SplashImage();

        // Validate image file type
//        if (!Objects.requireNonNull(productImage.getContentType()).startsWith("image/webp")) {
//            return ResponseEntity.badRequest().body("File type must be webp!");
//        }

//        System.out.println(productRequestDTO.getName());



        File LOGOFile = new File("src/main/resources/static/LOGOs/",  "LOGO.webp");
        try (OutputStream os = new FileOutputStream(LOGOFile)) {
            os.write(LOGO.getBytes());
        }
        File SplashScreenFile = new File("src/main/resources/static/ProductImages/", "SplashScreen.webp");
        try (OutputStream os = new FileOutputStream(SplashScreenFile)) {
            os.write(splashScreen.getBytes());
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


}
