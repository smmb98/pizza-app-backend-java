package dev.mohibullah.pizzaappbackendjava.services.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mohibullah.pizzaappbackendjava.dtos.request.ProductRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ProductResponseDTO;
import dev.mohibullah.pizzaappbackendjava.enums.Status;
import dev.mohibullah.pizzaappbackendjava.exceptions.EmptyItemsListException;
import dev.mohibullah.pizzaappbackendjava.exceptions.ItemNotFoundException;
import dev.mohibullah.pizzaappbackendjava.models.Category;
import dev.mohibullah.pizzaappbackendjava.models.Product;
import dev.mohibullah.pizzaappbackendjava.models.Products_Sizes_Prices;
import dev.mohibullah.pizzaappbackendjava.models.Size;
import dev.mohibullah.pizzaappbackendjava.repositories.*;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.ProductServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductServiceInterface {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final SizeRepository sizeRepository;
    private final Products_Sizes_PricesRepository productsSizesPricesRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) throws IOException {

//        Product product = productRepository.save(mapToEntity(productRequestDTO));
        Product product = mapToEntity(productRequestDTO);

        return mapToResponseDto(product);
    }

    @Override
    public BaseShowAllResponseDTO<ProductResponseDTO> showProducts(int page, int pageSize) {
        if (pageSize == 0) {
            List<Product> allProducts = productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            if (allProducts.isEmpty()) {
                throw new EmptyItemsListException("No Products in database");
            }

            List<ProductResponseDTO> content = allProducts.stream()
                    .map(product -> mapToResponseDto(product))
                    .toList();

            BaseShowAllResponseDTO<ProductResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(0);
            response.setPageSize(allProducts.size());
            response.setTotalElements(allProducts.size());
            response.setTotalPages(1);
            response.setLast(true);

            return response;
        } else {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "id"));
            Page<Product> products = productRepository.findAll(pageable);

            if (products.getTotalElements() == 0) {
                throw new EmptyItemsListException("No Products in database");
            }

            if (products.isEmpty()) {
                throw new EmptyItemsListException("No Products in page: " + products.getNumber());
            }

            List<Product> productList = products.getContent();
            List<ProductResponseDTO> content = productList.stream()
                    .map(product -> mapToResponseDto(product))
                    .toList();

            BaseShowAllResponseDTO<ProductResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(products.getNumber());
            response.setPageSize(products.getSize());
            response.setTotalElements(products.getTotalElements());
            response.setTotalPages(products.getTotalPages());
            response.setLast(products.isLast());

            return response;
        }
    }


    @Override
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ItemNotFoundException("Product could not be found"));
//        productRepository.save(mapToEntity(productRequestDTO, product));
        return mapToResponseDto(product);
    }


    private ProductResponseDTO mapToResponseDto(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setImageName(product.getImageName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setStatus(product.getStatus());

//        Category category = product.getCategory();
//        category.setProducts(null);
//        category.setSubCategories(null);
//        category.setToppings(null);
//        productResponseDTO.setCategory(category);

//        SubCategory subCategory = product.getSubCategory();
//        subCategory.setCategory(null);
//        subCategory.setProducts(null);
//        productResponseDTO.setSubCategory(subCategory);

        productResponseDTO.setCreatedAt(product.getCreatedAt());
        productResponseDTO.setUpdatedAt(product.getUpdatedAt());

        List<Products_Sizes_Prices> productsSizesPricesList = new ArrayList<>();
        for (int i = 0; i < product.getProductsSizesPrices().size(); i++) {
            Products_Sizes_Prices productsSizesPrices = product.getProductsSizesPrices().get(i);
//            productsSizesPrices.getSize();
            productsSizesPrices.setProduct(null);
            productsSizesPricesList.add(productsSizesPrices);
        }

//        productResponseDTO.setProductsSizesPrices(productsSizesPricesList);

        return productResponseDTO;
    }

    private Product mapToEntity(ProductRequestDTO productRequestDTO) throws IOException {
        Product product = new Product();

        Category category = categoryRepository.findById(Integer.valueOf(productRequestDTO.getCategoryId())).orElseThrow(() -> new ItemNotFoundException("Category could not be found"));
//        category.setProducts(null);
//        category.setSubCategories(null);
//        category.setToppings(null);
//        SubCategory subCategory = new SubCategory();
//
//        if (productRequestDTO.getSubCategoryId() != null) {
//            subCategory = subCategoryRepository.findById(Integer.valueOf(productRequestDTO.getSubCategoryId())).orElseThrow(() -> new ItemNotFoundException("SubCategory could not be found"));
////            subCategory.setCategory(null);
////            subCategory.setProducts(null);
//        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductRequestDTO.SizeIdDTO> sizeIdList = objectMapper.readValue(
                productRequestDTO.getSizeId(),
                new TypeReference<>() {
                }
        );

        List<Size> sizeList = new ArrayList<>();
        for (ProductRequestDTO.SizeIdDTO sizeIdDTO : sizeIdList) {
            Size size = sizeRepository.findById(sizeIdDTO.getId()).orElseThrow(() -> new ItemNotFoundException("One or more Sizes could not be found"));
            size.setProductsSizesPrices(null);
            size.setOrderItem(null);
            sizeList.add(size);
        }


        MultipartFile productImage = productRequestDTO.getProductImage();

        product.setName(productRequestDTO.getName());
        product.setImageName(productRequestDTO.getName().replace(" ", "_") + ".webp");
        product.setDescription(productRequestDTO.getDescription());
        product.setStatus(Status.ACTIVE);
        product.setCategory(category);
//        if (productRequestDTO.getSubCategoryId() != null) {
//            product.setSubCategory(subCategory);
//        }

        productRepository.save(product);

        for (int i = 0; i < sizeIdList.size(); i++) {
            Products_Sizes_Prices products_sizes_prices = new Products_Sizes_Prices();
            products_sizes_prices.setPrice(sizeIdList.get(i).getPrice());
            products_sizes_prices.setSize(sizeList.get(i));
            products_sizes_prices.setProduct(product);
            productsSizesPricesRepository.save(products_sizes_prices);
        }

        File file = new File("src/main/resources/static/ProductImages/", productRequestDTO.getName().replace(" ", "_") + ".webp");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(productImage.getBytes());
        }


        return product;
    }

//    private Product mapToEntity(ProductRequestDTO productRequestDTO, Product product) {
//        size.setMeasurement(sizeRequestDTO.getMeasurement());
//        size.setDescription(sizeRequestDTO.getDescription());
//
//        return product;
//    }
}
