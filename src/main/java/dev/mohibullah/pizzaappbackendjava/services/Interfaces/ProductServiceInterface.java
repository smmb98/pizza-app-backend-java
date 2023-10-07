package dev.mohibullah.pizzaappbackendjava.services.Interfaces;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ProductRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ProductResponseDTO;

import java.io.IOException;

public interface ProductServiceInterface {
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) throws IOException;

    BaseShowAllResponseDTO<ProductResponseDTO> showProducts(int page, int pageSize);

    ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, int productId) throws IOException;
    
}
