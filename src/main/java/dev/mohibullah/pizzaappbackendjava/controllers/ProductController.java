package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ProductRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ProductResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.services.implementations.ProductServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServiceImplementation productServiceImplementation;

    @PostMapping("addProduct")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @ModelAttribute ProductRequestDTO productRequestDTO) throws IOException {

//        MultipartFile productImage = productRequestDTO.getProductImage();
//        List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<>();
//
//        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
//        productResponseDTO.setId(1);
//        productResponseDTO.setName(productRequestDTO.getName());
//        productResponseDTO.setImageName(productRequestDTO.getName().replace(" ", "_") + ".webp");
//        productResponseDTO.setDescription(productRequestDTO.getDescription());
//        productResponseDTO.setStatus(Status.ACTIVE);
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<ProductRequestDTO.SizeIdDTO> sizeIdList = objectMapper.readValue(
//                productRequestDTO.getSizeId(),
//                new TypeReference<>() {
//                }
//        );
//
//        for (int i = 0; i < sizeIdList.size(); i++) {
//            Products_Sizes_Prices products_sizes_prices = new Products_Sizes_Prices();
//            products_sizes_prices.setId(i + 1);
//            products_sizes_prices.setPrice(sizeIdList.get(i).getPrice());
//            Size size = new Size();
//            size.setId(sizeIdList.get(i).getId());
//            size.setMeasurement("16");
//            size.setDescription("X-Large");
//            products_sizes_prices.setSize(size);
//            productsSizesPrices.add(products_sizes_prices);
//        }
//        productResponseDTO.setProductsSizesPrices(productsSizesPrices);
//
//        Category category = new Category();
//        category.setId(Integer.parseInt(productRequestDTO.getCategoryId()));
//        category.setName("Pizza");
//        productResponseDTO.setCategory(category);
//
//        if (productRequestDTO.getSubCategoryId() != null) {
//            SubCategory subCategory = new SubCategory();
//            subCategory.setId(Integer.parseInt(productRequestDTO.getSubCategoryId()));
//            subCategory.setName("Veggie Pizza Platter");
//            subCategory.setCategory(category);
//            productResponseDTO.setSubCategory(subCategory);
//        }
//
//        File file = new File("src/main/resources/static/ProductImages/", productRequestDTO.getName().replace(" ", "_") + ".webp");
//        try (OutputStream os = new FileOutputStream(file)) {
//            os.write(productImage.getBytes());
//        }
//
//
//        System.out.println(productResponseDTO);
////        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
//        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
        return new ResponseEntity<>(productServiceImplementation.createProduct(productRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("showProducts")
    public ResponseEntity<String> showProducts(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//       @GetMapping("showProducts")
//    public ResponseEntity<BaseShowAllResponseDTO<ProductResponseDTO>> showProducts(@RequestParam(value = "page", defaultValue = "1") int page,
//                                                                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }

//        List<ProductResponseDTO> productList = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//            List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<>();
//
//            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
//            productResponseDTO.setId(1);
//            productResponseDTO.setName("JAIN MASALA PIZZA");
//            productResponseDTO.setImageName("JAIN_MASALA_PIZZA.webp");
//            productResponseDTO.setDescription("Non-Garlic Onion Sauce, Cheese, Cilantro, Desi Achari Masala Sprinkled, Green Bell Pepper, Fresh Jalapeno");
//            productResponseDTO.setStatus(Status.ACTIVE);
//            List<ProductRequestDTO.SizeIdDTO> sizeIdList = new ArrayList<>();
//
//            sizeIdList.add(new ProductRequestDTO.SizeIdDTO(1, 12.0));
//            sizeIdList.add(new ProductRequestDTO.SizeIdDTO(2, 2.0));
//
//            for (int j = 0; j < sizeIdList.size(); j++) {
//                Products_Sizes_Prices products_sizes_prices = new Products_Sizes_Prices();
//                products_sizes_prices.setId(j + 1);
//                products_sizes_prices.setPrice(sizeIdList.get(j).getPrice());
//                Size size = new Size();
//                size.setId(sizeIdList.get(j).getId());
//                size.setMeasurement("16");
//                size.setDescription("X-Large");
//                products_sizes_prices.setSize(size);
//                productsSizesPrices.add(products_sizes_prices);
//            }
//            productResponseDTO.setProductsSizesPrices(productsSizesPrices);
//
//            Category category = new Category();
//            category.setId(i + 1);
//            category.setName("Pizza");
//            productResponseDTO.setCategory(category);
//
//            if (i % 2 != 0) {
//                SubCategory subCategory = new SubCategory();
//                subCategory.setId(i + 1);
//                subCategory.setName("Veggie Pizza Platter");
//                subCategory.setCategory(category);
//                productResponseDTO.setSubCategory(subCategory);
//            }
//
//            productList.add(productResponseDTO);
//        }
//        System.out.println(productList);
//
//
//        BaseShowAllResponseDTO<ProductResponseDTO> baseShowAllResponseDTO = new BaseShowAllResponseDTO<>();
//        baseShowAllResponseDTO.setContent(productList);
//        baseShowAllResponseDTO.setPage(page);
//        baseShowAllResponseDTO.setPageSize(pageSize);
//        baseShowAllResponseDTO.setTotalPages(1);
//        baseShowAllResponseDTO.setTotalElements(productList.size());
//        baseShowAllResponseDTO.setLast(true);
//        return new ResponseEntity<>(baseShowAllResponseDTO, HttpStatus.OK);
        return new ResponseEntity<>("TRue", HttpStatus.OK);
    }

    @PutMapping("updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@Valid @ModelAttribute ProductRequestDTO productRequestDTO, @PathVariable("id") int productId) throws IOException {
//    public ResponseEntity<ProductResponseDTO> updateProduct(@Valid @ModelAttribute ProductRequestDTO productRequestDTO, @PathVariable("id") int productId) throws IOException {

//        MultipartFile productImage = productRequestDTO.getProductImage();
//        List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<>();
//
//        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
//        productResponseDTO.setId(productId);
//        productResponseDTO.setName(productRequestDTO.getName());
//        productResponseDTO.setImageName(productRequestDTO.getName().replace(" ", "_") + ".webp");
//        productResponseDTO.setDescription(productRequestDTO.getDescription());
//        productResponseDTO.setStatus(Status.valueOf(productRequestDTO.getStatus()));
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<ProductRequestDTO.SizeIdDTO> sizeIdList = objectMapper.readValue(
//                productRequestDTO.getSizeId(),
//                new TypeReference<>() {
//                }
//        );
//
//        for (int i = 0; i < sizeIdList.size(); i++) {
//            Products_Sizes_Prices products_sizes_prices = new Products_Sizes_Prices();
//            products_sizes_prices.setId(i + 1);
//            products_sizes_prices.setPrice(sizeIdList.get(i).getPrice());
//            Size size = new Size();
//            size.setId(sizeIdList.get(i).getId());
//            size.setMeasurement("16");
//            size.setDescription("X-Large");
//            products_sizes_prices.setSize(size);
//            productsSizesPrices.add(products_sizes_prices);
//        }
//        productResponseDTO.setProductsSizesPrices(productsSizesPrices);
//
//        Category category = new Category();
//        category.setId(Integer.parseInt(productRequestDTO.getCategoryId()));
//        category.setName("Pizza");
//        productResponseDTO.setCategory(category);
//
//        if (productRequestDTO.getSubCategoryId() != null) {
//            SubCategory subCategory = new SubCategory();
//            subCategory.setId(Integer.parseInt(productRequestDTO.getSubCategoryId()));
//            subCategory.setName("Veggie Pizza Platter");
//            subCategory.setCategory(category);
//            productResponseDTO.setSubCategory(subCategory);
//        }
//
//        File file = new File("src/main/resources/static/ProductImages/", productRequestDTO.getName().replace(" ", "_") + ".webp");
//        try (OutputStream os = new FileOutputStream(file)) {
//            os.write(productImage.getBytes());
//        }
//
//
//        System.out.println(productResponseDTO);
////        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
//        return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
        return new ResponseEntity<>("TRue", HttpStatus.OK);


    }
}
