package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ProductRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ProductResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.services.implementations.ProductServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServiceImplementation productServiceImplementation;

    @PostMapping("addProduct")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @Valid @ModelAttribute ProductRequestDTO productRequestDTO) throws IOException {

        return new ResponseEntity<>(productServiceImplementation.createProduct(productRequestDTO), HttpStatus.CREATED);
    }


    @GetMapping("showProducts")
    public ResponseEntity<BaseShowAllResponseDTO<ProductResponseDTO>> showProducts(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }

        return new ResponseEntity<>(productServiceImplementation.showProducts(page, pageSize), HttpStatus.OK);
    }

    @PutMapping("updateProduct/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @Valid @ModelAttribute ProductRequestDTO productRequestDTO,
            @PathVariable("id") int productId) throws IOException {

        return new ResponseEntity<>(productServiceImplementation.updateProduct(
                productRequestDTO, productId),
                HttpStatus.OK);
    }
}
