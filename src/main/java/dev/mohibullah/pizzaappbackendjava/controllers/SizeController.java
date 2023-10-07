package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SizeRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SizeResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.services.implementations.SizeServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/size")
public class SizeController {

    private final SizeServiceImplementation sizeServiceImplementation;

    @PostMapping("addSize")
    public ResponseEntity<SizeResponseDTO> createSize(@Valid @RequestBody SizeRequestDTO sizeRequestDTO) {

        return new ResponseEntity<>(sizeServiceImplementation.createSize(sizeRequestDTO), HttpStatus.CREATED);
    }


    @GetMapping("showSizes")
    public ResponseEntity<BaseShowAllResponseDTO<SizeResponseDTO>> showSizes(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }


        return new ResponseEntity<>(sizeServiceImplementation.showSizes(page, pageSize), HttpStatus.OK);
    }


    @PutMapping("updateSize/{id}")
    public ResponseEntity<SizeResponseDTO> updateSize(
            @Valid @RequestBody SizeRequestDTO sizeRequestDTO,
            @PathVariable("id") int sizeId) {

        return new ResponseEntity<>(sizeServiceImplementation.updateSize(sizeRequestDTO, sizeId), HttpStatus.CREATED);
    }


}
