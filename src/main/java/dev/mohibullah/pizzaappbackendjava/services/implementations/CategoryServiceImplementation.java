package dev.mohibullah.pizzaappbackendjava.services.implementations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.CategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.*;
import dev.mohibullah.pizzaappbackendjava.exceptions.EmptyItemsListException;
import dev.mohibullah.pizzaappbackendjava.exceptions.ItemNotFoundException;
import dev.mohibullah.pizzaappbackendjava.models.*;
import dev.mohibullah.pizzaappbackendjava.repositories.CategoryRepository;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.CategoryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryServiceInterface {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {

        Category category = categoryRepository.save(mapToEntity(categoryRequestDTO));

        return mapToResponseDto(category);
    }

    @Override
    public BaseShowAllResponseDTO<CategoryResponseDTO> showCategories(int page, int pageSize) {
        if (pageSize == 0) {
            List<Category> allCategories = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            if (allCategories.isEmpty()) {
                throw new EmptyItemsListException("No Categories in database");
            }

            List<CategoryResponseDTO> content = allCategories.stream()
                    .map(category -> mapToResponseDto(category))
                    .toList();

            BaseShowAllResponseDTO<CategoryResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(0);
            response.setPageSize(allCategories.size());
            response.setTotalElements(allCategories.size());
            response.setTotalPages(1);
            response.setLast(true);

            return response;
        } else {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "id"));
            Page<Category> categories = categoryRepository.findAll(pageable);

            if (categories.getTotalElements() == 0) {
                throw new EmptyItemsListException("No Categories in database");
            }

            if (categories.isEmpty()) {
                throw new EmptyItemsListException("No Categories in page: " + categories.getNumber());
            }

            List<Category> listOfCategory = categories.getContent();
            List<CategoryResponseDTO> content = listOfCategory.stream()
                    .map(category -> mapToResponseDto(category))
                    .toList();

            BaseShowAllResponseDTO<CategoryResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(categories.getNumber());
            response.setPageSize(categories.getSize());
            response.setTotalElements(categories.getTotalElements());
            response.setTotalPages(categories.getTotalPages());
            response.setLast(categories.isLast());

            return response;
        }
    }

    @Override
    public CategoryResponseDTO showCategoryById(int categoryId) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ItemNotFoundException("Category could not be found"));
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());

        if (category.getSubCategories().size() > 0) {
            List<SubCategoryResponseDTO> subCategoryResponseDTOList = new ArrayList<>();
            for (SubCategory subCategory : category.getSubCategories()) {
                SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
                subCategoryResponseDTO.setId(subCategory.getId());
                subCategoryResponseDTO.setName(subCategory.getName());

                List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
                for (Product product : subCategory.getProducts()) {
                    ProductResponseDTO productResponseDTO = new ProductResponseDTO();
                    productResponseDTO.setId(product.getId());
                    productResponseDTO.setName(product.getName());
                    productResponseDTO.setImageName(product.getImageName());
                    productResponseDTO.setDescription(product.getDescription());
                    productResponseDTO.setStatus(product.getStatus());

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

                    productResponseDTOList.add(productResponseDTO);
                }
                productResponseDTOList.sort(Comparator.comparing(ProductResponseDTO::getId));
                subCategoryResponseDTO.setProducts(productResponseDTOList);

                subCategoryResponseDTOList.add(subCategoryResponseDTO);
            }
            subCategoryResponseDTOList.sort(Comparator.comparing(SubCategoryResponseDTO::getId));

            categoryResponseDTO.setSubCategories(subCategoryResponseDTOList);
        } else {
            List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
            for (Product product : category.getProducts()) {
                ProductResponseDTO productResponseDTO = new ProductResponseDTO();
                productResponseDTO.setId(product.getId());
                productResponseDTO.setName(product.getName());
                productResponseDTO.setImageName(product.getImageName());
                productResponseDTO.setDescription(product.getDescription());
                productResponseDTO.setStatus(product.getStatus());

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

                productResponseDTOList.add(productResponseDTO);
            }
            productResponseDTOList.sort(Comparator.comparing(ProductResponseDTO::getId));

            categoryResponseDTO.setProducts(productResponseDTOList);
        }


        categoryResponseDTO.setCreatedAt(category.getCreatedAt());
        categoryResponseDTO.setUpdatedAt(category.getUpdatedAt());

        return categoryResponseDTO;
    }

    @Override
    public CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO, int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ItemNotFoundException("Category could not be found"));
        categoryRepository.save(mapToEntity(categoryRequestDTO, category));

        return mapToResponseDto(category);
    }


    private CategoryResponseDTO mapToResponseDto(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setCreatedAt(category.getCreatedAt());
        categoryResponseDTO.setUpdatedAt(category.getUpdatedAt());
        return categoryResponseDTO;

    }

    private Category mapToEntity(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        return category;
    }

    private Category mapToEntity(CategoryRequestDTO categoryRequestDTO, Category category) {
        category.setName(categoryRequestDTO.getName());
        return category;
    }
}
