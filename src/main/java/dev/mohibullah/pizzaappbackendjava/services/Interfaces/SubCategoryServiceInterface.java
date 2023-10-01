package dev.mohibullah.pizzaappbackendjava.services.Interfaces;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SubCategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SubCategoryResponseDTO;

public interface SubCategoryServiceInterface {
    SubCategoryResponseDTO createSubCategory(SubCategoryRequestDTO subCategoryRequestDTO);

    BaseShowAllResponseDTO<SubCategoryResponseDTO> showSubCategories(int page, int pageSize);

    SubCategoryResponseDTO updateSubCategory(SubCategoryRequestDTO subCategoryRequestDTO, int subCategoryId);
}
