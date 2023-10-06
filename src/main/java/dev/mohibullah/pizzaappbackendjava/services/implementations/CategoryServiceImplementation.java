package dev.mohibullah.pizzaappbackendjava.services.implementations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.CategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.CategoryResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.EmptyItemsListException;
import dev.mohibullah.pizzaappbackendjava.exceptions.ItemNotFoundException;
import dev.mohibullah.pizzaappbackendjava.models.Category;
import dev.mohibullah.pizzaappbackendjava.models.Product;
import dev.mohibullah.pizzaappbackendjava.models.SubCategory;
import dev.mohibullah.pizzaappbackendjava.repositories.CategoryRepository;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.CategoryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
            List<SubCategory> subCategories = category.getSubCategories();
            for (SubCategory subCategory : subCategories) {
                List<Product> products = subCategory.getProducts();
                subCategory.setProducts(products);
                // By setting the category to null it will not be fetched from the database
                // Only with lazy loading enabled
                subCategory.setCategory(null);
            }

            categoryResponseDTO.setSubCategories(subCategories);
        } else {
            categoryResponseDTO.setProducts(category.getProducts());
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
