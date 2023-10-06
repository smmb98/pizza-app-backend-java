package dev.mohibullah.pizzaappbackendjava.services.Interfaces;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SizeRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SizeResponseDTO;

public interface SizeServiceInterface {
    SizeResponseDTO createSize(SizeRequestDTO sizeRequestDTO);

    BaseShowAllResponseDTO<SizeResponseDTO> showSizes(int page, int pageSize);

    SizeResponseDTO updateSize(SizeRequestDTO sizeRequestDTO, int sizeId);
}
