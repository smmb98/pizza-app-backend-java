package dev.mohibullah.pizzaappbackendjava.services.Interfaces;

import dev.mohibullah.pizzaappbackendjava.dtos.request.CategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.CategoryResponseDTO;

public interface CategoryServiceInterface {
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);

    BaseShowAllResponseDTO<CategoryResponseDTO> showCategories(int page, int pageSize);

    CategoryResponseDTO showCategoryById(int categoryId);

    CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequestDTO, int categoryId);
}
