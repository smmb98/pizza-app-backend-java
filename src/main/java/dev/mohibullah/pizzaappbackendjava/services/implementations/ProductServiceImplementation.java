package dev.mohibullah.pizzaappbackendjava.services.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mohibullah.pizzaappbackendjava.dtos.request.ProductRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.*;
import dev.mohibullah.pizzaappbackendjava.enums.Status;
import dev.mohibullah.pizzaappbackendjava.exceptions.EmptyItemsListException;
import dev.mohibullah.pizzaappbackendjava.exceptions.ItemNotFoundException;
import dev.mohibullah.pizzaappbackendjava.models.*;
import dev.mohibullah.pizzaappbackendjava.repositories.*;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.ProductServiceInterface;
import jakarta.transaction.Transactional;
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
import java.util.Comparator;
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
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, int productId) throws IOException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ItemNotFoundException("Product could not be found"));

        return mapToResponseDto(mapToEntity(productRequestDTO, product));
    }


    private ProductResponseDTO mapToResponseDto(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setImageName(product.getImageName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setStatus(product.getStatus());

        SubCategory subCategory = product.getSubCategory();
        if (subCategory != null) {
            SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
            subCategoryResponseDTO.setId(subCategory.getId());
            subCategoryResponseDTO.setName(subCategory.getName());
            subCategoryResponseDTO.setCreatedAt(subCategory.getCreatedAt());
            subCategoryResponseDTO.setUpdatedAt(subCategory.getUpdatedAt());
            productResponseDTO.setSubCategory(subCategoryResponseDTO);
        }

        Category category = product.getCategory();
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setCreatedAt(category.getCreatedAt());
        categoryResponseDTO.setUpdatedAt(category.getUpdatedAt());
        productResponseDTO.setCategory(categoryResponseDTO);

        List<ProductResponseDTO.Products_Sizes_PricesResponseDTO> productsSizesPricesResponseDTOList = new ArrayList<>();
        for (Products_Sizes_Prices productsSizesPrice : product.getProductsSizesPrices()) {
            ProductResponseDTO.Products_Sizes_PricesResponseDTO productsSizesPricesResponseDTO = new ProductResponseDTO.Products_Sizes_PricesResponseDTO();
            productsSizesPricesResponseDTO.setId(productsSizesPrice.getId());
            productsSizesPricesResponseDTO.setPrice(productsSizesPrice.getPrice());

            Size size = productsSizesPrice.getSize();
            SizeResponseDTO sizeResponseDTO = new SizeResponseDTO();
            sizeResponseDTO.setId(size.getId());
            sizeResponseDTO.setMeasurement(size.getMeasurement());
            sizeResponseDTO.setDescription(size.getDescription());
            sizeResponseDTO.setCreatedAt(size.getCreatedAt());
            sizeResponseDTO.setUpdatedAt(size.getUpdatedAt());
            productsSizesPricesResponseDTO.setSize(sizeResponseDTO);

            productsSizesPricesResponseDTO.setCreatedAt(productsSizesPrice.getCreatedAt());
            productsSizesPricesResponseDTO.setUpdatedAt(productsSizesPrice.getUpdatedAt());

            productsSizesPricesResponseDTOList.add(productsSizesPricesResponseDTO);
        }
        productsSizesPricesResponseDTOList.sort(Comparator.comparing(ProductResponseDTO.Products_Sizes_PricesResponseDTO::getId));
        productResponseDTO.setProductsSizesPrices(productsSizesPricesResponseDTOList);

        productResponseDTO.setCreatedAt(product.getCreatedAt());
        productResponseDTO.setUpdatedAt(product.getUpdatedAt());

        return productResponseDTO;
    }

    private Product mapToEntity(ProductRequestDTO productRequestDTO) throws IOException {
        Product product = new Product();

        product.setName(productRequestDTO.getName());
        product.setImageName(productRequestDTO.getName().replace(" ", "_") + ".webp");
        product.setDescription(productRequestDTO.getDescription());
        product.setStatus(Status.ACTIVE);

        Category category = categoryRepository.findById(Integer.valueOf(productRequestDTO.getCategoryId())).orElseThrow(() -> new ItemNotFoundException("Category could not be found"));
        product.setCategory(category);

        if (productRequestDTO.getSubCategoryId() != null) {
            SubCategory subCategory = subCategoryRepository.findById(Integer.valueOf(productRequestDTO.getSubCategoryId())).orElseThrow(() -> new ItemNotFoundException("SubCategory could not be found"));
            product.setSubCategory(subCategory);
        }

        MultipartFile productImage = productRequestDTO.getProductImage();
        File file = new File("src/main/resources/static/ProductImages/", productRequestDTO.getName().replace(" ", "_") + ".webp");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(productImage.getBytes());
        }


        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductRequestDTO.SizeIdDTO> sizeIdList = objectMapper.readValue(
                productRequestDTO.getSizeId(),
                new TypeReference<>() {
                }
        );

        List<Size> sizeList = new ArrayList<>();
        for (ProductRequestDTO.SizeIdDTO sizeIdDTO : sizeIdList) {
            Size size = sizeRepository.findById(sizeIdDTO.getId()).orElseThrow(() -> new ItemNotFoundException("One or more Sizes could not be found"));
            sizeList.add(size);
        }

        productRepository.save(product);

        List<Products_Sizes_Prices> productsSizesPrices = new ArrayList<>();
        for (int i = 0; i < sizeIdList.size(); i++) {
            Products_Sizes_Prices products_sizes_prices = new Products_Sizes_Prices();
            products_sizes_prices.setPrice(sizeIdList.get(i).getPrice());
            products_sizes_prices.setSize(sizeList.get(i));
            products_sizes_prices.setProduct(product);
            productsSizesPricesRepository.save(products_sizes_prices);
            productsSizesPrices.add(products_sizes_prices);
        }
        product.setProductsSizesPrices(productsSizesPrices);


        return product;
    }

    @Transactional
    private Product mapToEntity(ProductRequestDTO productRequestDTO, Product product) throws IOException {
        product.setName(productRequestDTO.getName());
        product.setImageName(productRequestDTO.getName().replace(" ", "_") + ".webp");
        product.setDescription(productRequestDTO.getDescription());
        product.setStatus(Status.INACTIVE.getLabel().equals(productRequestDTO.getStatus()) ? Status.INACTIVE : Status.ACTIVE);
        Category category = categoryRepository.findById(Integer.valueOf(productRequestDTO.getCategoryId())).orElseThrow(() -> new ItemNotFoundException("Category could not be found"));
        product.setCategory(category);

        if (productRequestDTO.getSubCategoryId() != null) {
            SubCategory subCategory = subCategoryRepository.findById(Integer.valueOf(productRequestDTO.getSubCategoryId())).orElseThrow(() -> new ItemNotFoundException("SubCategory could not be found"));
            product.setSubCategory(subCategory);
        } else {
            product.setSubCategory(null);
        }

        MultipartFile productImage = productRequestDTO.getProductImage();
        File file = new File("src/main/resources/static/ProductImages/", productRequestDTO.getName().replace(" ", "_") + ".webp");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(productImage.getBytes());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductRequestDTO.SizeIdDTO> sizeIdList = objectMapper.readValue(
                productRequestDTO.getSizeId(),
                new TypeReference<>() {
                }
        );

        List<Size> sizeList = new ArrayList<>();
        for (ProductRequestDTO.SizeIdDTO sizeIdDTO : sizeIdList) {
            Size size = sizeRepository.findById(sizeIdDTO.getId()).orElseThrow(() -> new ItemNotFoundException("One or more Sizes could not be found"));
            sizeList.add(size);
        }

        productRepository.save(product);


////////////////////////////////////////////////////////////////////////
///////////////////////////// Need Help Here ///////////////////////////
////////////////////////////////////////////////////////////////////////
//        productsSizesPricesRepository.deleteById(20); // Here delete works

        List<Products_Sizes_Prices> productsSizesPrices = product.getProductsSizesPrices();

        int largestSizeLength = Math.max(sizeIdList.size(), productsSizesPrices.size());


        for (int i = 0; i < largestSizeLength; i++) {
            if (i < productsSizesPrices.size()) {
                Products_Sizes_Prices products_sizes_prices = productsSizesPrices.get(i);
                if (i < sizeList.size()) {
                    System.out.println("On Update");
                    products_sizes_prices.setPrice(sizeIdList.get(i).getPrice());
                    products_sizes_prices.setSize(sizeList.get(i));
                    productsSizesPricesRepository.save(products_sizes_prices);
                } else {
                    System.out.println("On Delete");
                    try {
                        productsSizesPricesRepository.delete(products_sizes_prices);
                        productsSizesPricesRepository.deleteByIdSql(products_sizes_prices.getId());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
//                    productsSizesPricesRepository.delete(products_sizes_prices); // This delete command is not working, even though above code executes
                }
            } else {
                Products_Sizes_Prices products_sizes_prices = new Products_Sizes_Prices();
                products_sizes_prices.setPrice(sizeIdList.get(i).getPrice());
                products_sizes_prices.setSize(sizeList.get(i));
                products_sizes_prices.setProduct(product);
                productsSizesPricesRepository.save(products_sizes_prices);
            }
        }

////////////////////////////////////////////////////////////////////////
//        List<Products_Sizes_Prices> productsSizesPrices = product.getProductsSizesPrices();
//
//        int largestSizeLength = Math.max(sizeIdList.size(), productsSizesPrices.size());
//
//        List<Products_Sizes_Prices> productsToSaveOrUpdate = new ArrayList<>();
//        List<Products_Sizes_Prices> productsToDelete = new ArrayList<>();
//
//        for (int i = 0; i < largestSizeLength; i++) {
//            if (i < productsSizesPrices.size()) {
//                Products_Sizes_Prices products_sizes_prices = productsSizesPrices.get(i);
//                if (i < sizeList.size()) {
//                    System.out.println("On Update");
//                    products_sizes_prices.setPrice(sizeIdList.get(i).getPrice());
//                    products_sizes_prices.setSize(sizeList.get(i));
////                    productsToSaveOrUpdate.add(products_sizes_prices);
//                } else {
//                    System.out.println("On Delete");
//                    productsToDelete.add(products_sizes_prices);
//                }
//            } else {
//                Products_Sizes_Prices products_sizes_prices = new Products_Sizes_Prices();
//                products_sizes_prices.setPrice(sizeIdList.get(i).getPrice());
//                products_sizes_prices.setSize(sizeList.get(i));
//                products_sizes_prices.setProduct(product);
//                productsToSaveOrUpdate.add(products_sizes_prices);
//            }
//        }
//
//// Batch save or update
//        if (!productsToSaveOrUpdate.isEmpty()) {
//            productsSizesPricesRepository.saveAll(productsToSaveOrUpdate);
//        }
//
//// Batch delete
//        if (!productsToDelete.isEmpty()) {
//            productsSizesPricesRepository.deleteAll(productsToDelete);
//        }
//

        return product;
    }
}
