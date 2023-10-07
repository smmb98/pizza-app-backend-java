package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ToppingRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ToppingResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.services.implementations.ToppingServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topping")
public class ToppingController {

    private final ToppingServiceImplementation toppingServiceImplementation;

    @PostMapping("addTopping")
    public ResponseEntity<ToppingResponseDTO> createTopping(
            @Valid @RequestBody ToppingRequestDTO toppingRequestDTO) {

        return new ResponseEntity<>(toppingServiceImplementation.createTopping(toppingRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("showToppings")
    public ResponseEntity<BaseShowAllResponseDTO<ToppingResponseDTO>> showToppings(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }

        return new ResponseEntity<>(toppingServiceImplementation.showToppings(page, pageSize), HttpStatus.OK);
    }


    @PutMapping("updateTopping/{id}")
    public ResponseEntity<ToppingResponseDTO> updateTopping(
            @Valid @RequestBody ToppingRequestDTO toppingRequestDTO,
            @PathVariable("id") int toppingId) {

        return new ResponseEntity<>(toppingServiceImplementation.updateTopping(toppingRequestDTO, toppingId), HttpStatus.CREATED);
    }

}
