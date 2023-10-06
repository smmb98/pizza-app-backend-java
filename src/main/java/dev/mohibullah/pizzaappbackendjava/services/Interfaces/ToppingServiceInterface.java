package dev.mohibullah.pizzaappbackendjava.services.Interfaces;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ToppingRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ToppingResponseDTO;

public interface ToppingServiceInterface {
    ToppingResponseDTO createTopping(ToppingRequestDTO toppingRequestDTO);

    BaseShowAllResponseDTO<ToppingResponseDTO> showToppings(int page, int pageSize);

    ToppingResponseDTO updateTopping(ToppingRequestDTO toppingRequestDTO, int toppingId);
}
