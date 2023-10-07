package dev.mohibullah.pizzaappbackendjava.services.implementations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ToppingRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.CategoryResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ToppingResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.EmptyItemsListException;
import dev.mohibullah.pizzaappbackendjava.exceptions.ItemNotFoundException;
import dev.mohibullah.pizzaappbackendjava.models.Category;
import dev.mohibullah.pizzaappbackendjava.models.Topping;
import dev.mohibullah.pizzaappbackendjava.repositories.CategoryRepository;
import dev.mohibullah.pizzaappbackendjava.repositories.ToppingRepository;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.ToppingServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ToppingServiceImplementation implements ToppingServiceInterface {

    private final ToppingRepository toppingRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ToppingResponseDTO createTopping(ToppingRequestDTO toppingRequestDTO) {

        Topping topping = toppingRepository.save(mapToEntity(toppingRequestDTO));

        return mapToResponseDto(topping);
    }

    @Override
    public BaseShowAllResponseDTO<ToppingResponseDTO> showToppings(int page, int pageSize) {
        if (pageSize == 0) {
            List<Topping> allToppings = toppingRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            if (allToppings.isEmpty()) {
                throw new EmptyItemsListException("No Toppings in database");
            }

            List<ToppingResponseDTO> content = allToppings.stream()
                    .map(size -> mapToResponseDto(size))
                    .toList();

            BaseShowAllResponseDTO<ToppingResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(0);
            response.setPageSize(allToppings.size());
            response.setTotalElements(allToppings.size());
            response.setTotalPages(1);
            response.setLast(true);

            return response;
        } else {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "id"));
            Page<Topping> toppings = toppingRepository.findAll(pageable);

            if (toppings.getTotalElements() == 0) {
                throw new EmptyItemsListException("No Toppings in database");
            }

            if (toppings.isEmpty()) {
                throw new EmptyItemsListException("No Toppings in page: " + toppings.getNumber());
            }

            List<Topping> listOfSize = toppings.getContent();
            List<ToppingResponseDTO> content = listOfSize.stream()
                    .map(topping -> mapToResponseDto(topping))
                    .toList();

            BaseShowAllResponseDTO<ToppingResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(toppings.getNumber());
            response.setPageSize(toppings.getSize());
            response.setTotalElements(toppings.getTotalElements());
            response.setTotalPages(toppings.getTotalPages());
            response.setLast(toppings.isLast());

            return response;
        }
    }


    @Override
    public ToppingResponseDTO updateTopping(ToppingRequestDTO toppingRequestDTO, int toppingId) {
        Topping topping = toppingRepository.findById(toppingId).orElseThrow(() -> new ItemNotFoundException("Topping could not be found"));
        toppingRepository.save(mapToEntity(toppingRequestDTO, topping));
        return mapToResponseDto(topping);
    }


    private ToppingResponseDTO mapToResponseDto(Topping topping) {
        ToppingResponseDTO toppingResponseDTO = new ToppingResponseDTO();
        toppingResponseDTO.setId(topping.getId());
        toppingResponseDTO.setName(topping.getName());
        toppingResponseDTO.setPrice(topping.getPrice());

        List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
        for (Category category : topping.getCategories()) {

            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(category.getId());
            categoryResponseDTO.setName(category.getName());
            categoryResponseDTO.setCreatedAt(category.getCreatedAt());
            categoryResponseDTO.setUpdatedAt(category.getUpdatedAt());
            categoryResponseDTOList.add(categoryResponseDTO);
        }
        toppingResponseDTO.setCategories(categoryResponseDTOList);

        toppingResponseDTO.setCreatedAt(topping.getCreatedAt());
        toppingResponseDTO.setUpdatedAt(topping.getUpdatedAt());

        return toppingResponseDTO;
    }

    private Topping mapToEntity(ToppingRequestDTO toppingRequestDTO) {
        Topping topping = new Topping();
        topping.setName(toppingRequestDTO.getName());
        topping.setPrice(toppingRequestDTO.getPrice());

        Set<Category> categorySet = topping.getCategories();
        for (Map<String, Integer> categoryId : toppingRequestDTO.getCategories()) {
            Category category = categoryRepository.findById(categoryId.get("id")).orElseThrow(() -> new ItemNotFoundException("One or more categories could not be found"));
            category.getToppings().add(topping); // Update the inverse side
            categorySet.add(category);
        }
        topping.setCategories(categorySet);

        return topping;
    }

    private Topping mapToEntity(ToppingRequestDTO toppingRequestDTO, Topping topping) {
        topping.setName(toppingRequestDTO.getName());
        topping.setPrice(toppingRequestDTO.getPrice());

        Set<Category> categorySet = new HashSet<>();
        for (Map<String, Integer> categoryId : toppingRequestDTO.getCategories()) {
            Category category = categoryRepository.findById(categoryId.get("id")).orElseThrow(() -> new ItemNotFoundException("One or more categories could not be found"));

            category.getToppings().add(topping); // Update the inverse side
            categorySet.add(category);
        }

        // Remove the topping from categories that are not in the categorySet
        for (Category existingCategory : topping.getCategories()) {
            if (!categorySet.contains(existingCategory)) {
                existingCategory.getToppings().remove(topping);
            }
        }

        topping.setCategories(categorySet);

        return topping;
    }
}
