package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ReviewRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ReviewResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.InvalidPaginationException;
import dev.mohibullah.pizzaappbackendjava.services.implementations.ReviewServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewServiceImplementation reviewServiceImplementation;

    @PostMapping("addReview")
    public ResponseEntity<ReviewResponseDTO> createReview(
            @Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {

        return new ResponseEntity<>(reviewServiceImplementation.createReview(reviewRequestDTO), HttpStatus.CREATED);

    }

    @GetMapping("showReviews")
    public ResponseEntity<BaseShowAllResponseDTO<ReviewResponseDTO>> showReviews(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }
        
        return new ResponseEntity<>(reviewServiceImplementation.showReviews(page, pageSize), HttpStatus.OK);

    }
}
