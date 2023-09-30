package dev.mohibullah.pizzaappbackendjava.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mohibullah.pizzaappbackendjava.dtos.request.ProductRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ProductResponseDTO;
import dev.mohibullah.pizzaappbackendjava.models.Category;
import dev.mohibullah.pizzaappbackendjava.models.Products_Sizes_Prices;
import dev.mohibullah.pizzaappbackendjava.models.Size;
import dev.mohibullah.pizzaappbackendjava.models.SubCategory;
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

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @PostMapping("addProduct")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @ModelAttribute ProductRequestDTO productRequestDTO) throws IOException {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        MultipartFile productImage = productRequestDTO.getProductImage();
        List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<>();

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(1);
        productResponseDTO.setName(productRequestDTO.getName());
        productResponseDTO.setImageName(productRequestDTO.getName().replace(" ", "_") + ".webp");
        productResponseDTO.setDescription(productRequestDTO.getDescription());
        productResponseDTO.setStatus("Active");
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductRequestDTO.SizeIdDTO> sizeIdList = objectMapper.readValue(
                productRequestDTO.getSizeId(),
                new TypeReference<>() {
                }
        );

        for (int i = 0; i < sizeIdList.size(); i++) {
            Products_Sizes_Prices products_sizes_prices = new Products_Sizes_Prices();
            products_sizes_prices.setId(i + 1);
            products_sizes_prices.setPrice(sizeIdList.get(i).getPrice());
            Size size = new Size();
            size.setId(sizeIdList.get(i).getId());
            size.setMeasurement("16");
            size.setDescription("X-Large");
            products_sizes_prices.setSize(size);
            productsSizesPrices.add(products_sizes_prices);
        }
        productResponseDTO.setProductsSizesPrices(productsSizesPrices);

        Category category = new Category();
        category.setId(Integer.parseInt(productRequestDTO.getCategoryId()));
        category.setName("Pizza");
        productResponseDTO.setCategory(category);

        if (productRequestDTO.getSubCategoryId() != null) {
            SubCategory subCategory = new SubCategory();
            subCategory.setId(Integer.parseInt(productRequestDTO.getSubCategoryId()));
            subCategory.setName("Veggie Pizza Platter");
            subCategory.setCategory(category);
            productResponseDTO.setSubCategory(subCategory);
        }

        File file = new File("src/main/resources/static/ProductImages/", productRequestDTO.getName().replace(" ", "_") + ".webp");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(productImage.getBytes());
        }


        System.out.println(productResponseDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("showProducts")
    public ResponseEntity<BaseResponseDTO<ProductResponseDTO>> showProducts(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<ProductResponseDTO> productList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<>();

            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setId(1);
            productResponseDTO.setName("JAIN MASALA PIZZA");
            productResponseDTO.setImageName("JAIN_MASALA_PIZZA.webp");
            productResponseDTO.setDescription("Non-Garlic Onion Sauce, Cheese, Cilantro, Desi Achari Masala Sprinkled, Green Bell Pepper, Fresh Jalapeno");
            productResponseDTO.setStatus("Active");
            ObjectMapper objectMapper = new ObjectMapper();
            List<ProductRequestDTO.SizeIdDTO> sizeIdList = new ArrayList<>();

            sizeIdList.add(new ProductRequestDTO.SizeIdDTO(1, 12.0));
            sizeIdList.add(new ProductRequestDTO.SizeIdDTO(2, 2.0));

            for (int j = 0; j < sizeIdList.size(); j++) {
                Products_Sizes_Prices products_sizes_prices = new Products_Sizes_Prices();
                products_sizes_prices.setId(j + 1);
                products_sizes_prices.setPrice(sizeIdList.get(j).getPrice());
                Size size = new Size();
                size.setId(sizeIdList.get(j).getId());
                size.setMeasurement("16");
                size.setDescription("X-Large");
                products_sizes_prices.setSize(size);
                productsSizesPrices.add(products_sizes_prices);
            }
            productResponseDTO.setProductsSizesPrices(productsSizesPrices);

            Category category = new Category();
            category.setId(i + 1);
            category.setName("Pizza");
            productResponseDTO.setCategory(category);

            if (i % 2 != 0) {
                SubCategory subCategory = new SubCategory();
                subCategory.setId(i + 1);
                subCategory.setName("Veggie Pizza Platter");
                subCategory.setCategory(category);
                productResponseDTO.setSubCategory(subCategory);
            }

            productList.add(productResponseDTO);
        }
        System.out.println(productList);


        BaseResponseDTO<ProductResponseDTO> baseResponseDTO = new BaseResponseDTO<>();
        baseResponseDTO.setContent(productList);
        baseResponseDTO.setPageNo(pageNo);
        baseResponseDTO.setPageSize(pageSize);
        baseResponseDTO.setTotalPages(1);
        baseResponseDTO.setTotalElements(productList.size());
        baseResponseDTO.setLast(true);
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }

    @PutMapping("updateProduct/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@Valid @ModelAttribute ProductRequestDTO productRequestDTO, @PathVariable("id") int productId) throws IOException {

        MultipartFile productImage = productRequestDTO.getProductImage();
        List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<>();

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(productId);
        productResponseDTO.setName(productRequestDTO.getName());
        productResponseDTO.setImageName(productRequestDTO.getName().replace(" ", "_") + ".webp");
        productResponseDTO.setDescription(productRequestDTO.getDescription());
        productResponseDTO.setStatus(productRequestDTO.getStatus());
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductRequestDTO.SizeIdDTO> sizeIdList = objectMapper.readValue(
                productRequestDTO.getSizeId(),
                new TypeReference<>() {
                }
        );

        for (int i = 0; i < sizeIdList.size(); i++) {
            Products_Sizes_Prices products_sizes_prices = new Products_Sizes_Prices();
            products_sizes_prices.setId(i + 1);
            products_sizes_prices.setPrice(sizeIdList.get(i).getPrice());
            Size size = new Size();
            size.setId(sizeIdList.get(i).getId());
            size.setMeasurement("16");
            size.setDescription("X-Large");
            products_sizes_prices.setSize(size);
            productsSizesPrices.add(products_sizes_prices);
        }
        productResponseDTO.setProductsSizesPrices(productsSizesPrices);

        Category category = new Category();
        category.setId(Integer.parseInt(productRequestDTO.getCategoryId()));
        category.setName("Pizza");
        productResponseDTO.setCategory(category);

        if (productRequestDTO.getSubCategoryId() != null) {
            SubCategory subCategory = new SubCategory();
            subCategory.setId(Integer.parseInt(productRequestDTO.getSubCategoryId()));
            subCategory.setName("Veggie Pizza Platter");
            subCategory.setCategory(category);
            productResponseDTO.setSubCategory(subCategory);
        }

        File file = new File("src/main/resources/static/ProductImages/", productRequestDTO.getName().replace(" ", "_") + ".webp");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(productImage.getBytes());
        }


        System.out.println(productResponseDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
    }
}
