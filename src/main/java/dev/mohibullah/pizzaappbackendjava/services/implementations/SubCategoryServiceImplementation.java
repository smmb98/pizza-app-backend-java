package dev.mohibullah.pizzaappbackendjava.services.implementations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SubCategoryRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SubCategoryResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.EmptyItemsListException;
import dev.mohibullah.pizzaappbackendjava.exceptions.ItemNotFoundException;
import dev.mohibullah.pizzaappbackendjava.models.Category;
import dev.mohibullah.pizzaappbackendjava.models.SubCategory;
import dev.mohibullah.pizzaappbackendjava.repositories.CategoryRepository;
import dev.mohibullah.pizzaappbackendjava.repositories.SubCategoryRepository;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.SubCategoryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImplementation implements SubCategoryServiceInterface {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public SubCategoryResponseDTO createSubCategory(SubCategoryRequestDTO subCategoryRequestDTO) {

        SubCategory subCategory = subCategoryRepository.save(mapToEntity(subCategoryRequestDTO));

        return mapToResponseDto(subCategory);
    }

    @Override
    public BaseShowAllResponseDTO<SubCategoryResponseDTO> showSubCategories(int page, int pageSize) {
        if (pageSize == 0) {
            List<SubCategory> allSubCategories = subCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            if (allSubCategories.isEmpty()) {
                throw new EmptyItemsListException("No SubCategories in database");
            }

            List<SubCategoryResponseDTO> content = allSubCategories.stream()
                    .map(subCategory -> mapToResponseDto(subCategory))
                    .toList();

            BaseShowAllResponseDTO<SubCategoryResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(0);
            response.setPageSize(allSubCategories.size());
            response.setTotalElements(allSubCategories.size());
            response.setTotalPages(1);
            response.setLast(true);

            return response;
        } else {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "id"));
            Page<SubCategory> subCategories = subCategoryRepository.findAll(pageable);

            if (subCategories.getTotalElements() == 0) {
                throw new EmptyItemsListException("No SubCategories in database");
            }

            if (subCategories.isEmpty()) {
                throw new EmptyItemsListException("No SubCategories in page: " + subCategories.getNumber());
            }

            List<SubCategory> listOfSubCategory = subCategories.getContent();
            List<SubCategoryResponseDTO> content = listOfSubCategory.stream()
                    .map(subCategory -> mapToResponseDto(subCategory))
                    .toList();

            BaseShowAllResponseDTO<SubCategoryResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(subCategories.getNumber());
            response.setPageSize(subCategories.getSize());
            response.setTotalElements(subCategories.getTotalElements());
            response.setTotalPages(subCategories.getTotalPages());
            response.setLast(subCategories.isLast());

            return response;
        }
    }


    @Override
    public SubCategoryResponseDTO updateSubCategory(SubCategoryRequestDTO subCategoryRequestDTO, int subCategoryId) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId).orElseThrow(() -> new ItemNotFoundException("SubCategory could not be found"));
        subCategoryRepository.save(mapToEntity(subCategoryRequestDTO, subCategory));
        return mapToResponseDto(subCategory);
    }


    private SubCategoryResponseDTO mapToResponseDto(SubCategory subCategory) {
        SubCategoryResponseDTO subCategoryResponseDTO = new SubCategoryResponseDTO();
//        Category category = subCategory.getCategory();
//        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

//        categoryResponseDTO.setId(category.getId());
//        categoryResponseDTO.setName(category.getName());
        subCategoryResponseDTO.setId(subCategory.getId());
        subCategoryResponseDTO.setName(subCategory.getName());
        subCategoryResponseDTO.setCategory(subCategory.getCategory());
//        subCategoryResponseDTO.setCategory(categoryResponseDTO);
        subCategoryResponseDTO.setCreatedAt(subCategory.getCreatedAt());
        subCategoryResponseDTO.setUpdatedAt(subCategory.getUpdatedAt());

        return subCategoryResponseDTO;
    }

    private SubCategory mapToEntity(SubCategoryRequestDTO subCategoryRequestDTO) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequestDTO.getName());
        Category category = categoryRepository.findById(subCategoryRequestDTO.getCategoryId()).orElseThrow(() -> new ItemNotFoundException("Category could not be found"));
        subCategory.setCategory(category);
        return subCategory;
    }

    private SubCategory mapToEntity(SubCategoryRequestDTO subCategoryRequestDTO, SubCategory subCategory) {
        subCategory.setName(subCategoryRequestDTO.getName());
        Category category = categoryRepository.findById(subCategoryRequestDTO.getCategoryId()).orElseThrow(() -> new ItemNotFoundException("Category could not be found"));
        subCategory.setCategory(category);
        return subCategory;
    }
}
