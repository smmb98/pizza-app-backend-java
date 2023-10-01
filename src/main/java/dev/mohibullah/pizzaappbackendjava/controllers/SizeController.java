package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.SizeRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.SizeResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/size")
public class SizeController {
    @PostMapping("addSize")
    public ResponseEntity<SizeResponseDTO> createSize(@Valid @RequestBody SizeRequestDTO sizeRequestDTO) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        SizeResponseDTO sizeResponseDTO = new SizeResponseDTO();
        sizeResponseDTO.setId(1);
        sizeResponseDTO.setMeasurement(sizeRequestDTO.getMeasurement());
        sizeResponseDTO.setDescription(sizeRequestDTO.getDescription());
        sizeResponseDTO.setCreatedAt(LocalDateTime.now());
        sizeResponseDTO.setUpdatedAt(LocalDateTime.now());
        System.out.println(sizeResponseDTO);
        return new ResponseEntity<>(sizeResponseDTO, HttpStatus.CREATED);
    }


    @GetMapping("showSizes")
    public ResponseEntity<BaseShowAllResponseDTO<SizeResponseDTO>> showSizes(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }

        List<SizeResponseDTO> sizeList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            SizeResponseDTO sizeResponseDTO = new SizeResponseDTO();
            sizeResponseDTO.setId(i + 1);
            sizeResponseDTO.setMeasurement("16");
            sizeResponseDTO.setDescription("X-Large");
            sizeResponseDTO.setCreatedAt(LocalDateTime.now());
            sizeResponseDTO.setUpdatedAt(LocalDateTime.now());
            sizeList.add(sizeResponseDTO);
        }
        System.out.println(sizeList);


        BaseShowAllResponseDTO<SizeResponseDTO> baseShowAllResponseDTO = new BaseShowAllResponseDTO<>();
        baseShowAllResponseDTO.setContent(sizeList);
        baseShowAllResponseDTO.setPage(page);
        baseShowAllResponseDTO.setPageSize(pageSize);
        baseShowAllResponseDTO.setTotalPages(1);
        baseShowAllResponseDTO.setTotalElements(sizeList.size());
        baseShowAllResponseDTO.setLast(true);
        return new ResponseEntity<>(baseShowAllResponseDTO, HttpStatus.OK);
    }


    @PutMapping("updateSize/{id}")
    public ResponseEntity<SizeResponseDTO> updateSize(@Valid @RequestBody SizeRequestDTO sizeRequestDTO, @PathVariable("id") int sizeId) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        SizeResponseDTO sizeResponseDTO = new SizeResponseDTO();
        sizeResponseDTO.setId(sizeId);
        sizeResponseDTO.setMeasurement(sizeRequestDTO.getMeasurement());
        sizeResponseDTO.setDescription(sizeRequestDTO.getDescription());
        sizeResponseDTO.setCreatedAt(LocalDateTime.now());
        sizeResponseDTO.setUpdatedAt(LocalDateTime.now());
        System.out.println(sizeResponseDTO);
        return new ResponseEntity<>(sizeResponseDTO, HttpStatus.CREATED);
    }


}
