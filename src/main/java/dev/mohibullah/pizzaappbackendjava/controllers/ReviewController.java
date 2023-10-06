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
    public ResponseEntity<ReviewResponseDTO> createReview(@Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

//        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
//        reviewResponseDTO.setId(1);
//        reviewResponseDTO.setRating(reviewRequestDTO.getRating());
//        reviewResponseDTO.setDescription(reviewRequestDTO.getDescription());
//        reviewResponseDTO.setCreatedAt(LocalDateTime.now());
//        reviewResponseDTO.setUpdatedAt(LocalDateTime.now());
//        System.out.println(reviewResponseDTO);
//        return new ResponseEntity<>(reviewResponseDTO, HttpStatus.CREATED);
        return new ResponseEntity<>(reviewServiceImplementation.createReview(reviewRequestDTO), HttpStatus.CREATED);

    }

    @GetMapping("showReviews")
    public ResponseEntity<BaseShowAllResponseDTO<ReviewResponseDTO>> showReviews(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (page < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }

//        List<ReviewResponseDTO> reviewList = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//            ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
//            reviewResponseDTO.setId(i + 1);
//            reviewResponseDTO.setRating(3.5);
//            reviewResponseDTO.setDescription("Late delivery");
//            reviewResponseDTO.setCreatedAt(LocalDateTime.now());
//            reviewResponseDTO.setUpdatedAt(LocalDateTime.now());
//            reviewList.add(reviewResponseDTO);
//        }
//        System.out.println(reviewList);
//
//
//        BaseShowAllResponseDTO<ReviewResponseDTO> baseShowAllResponseDTO = new BaseShowAllResponseDTO<>();
//        baseShowAllResponseDTO.setContent(reviewList);
//        baseShowAllResponseDTO.setPage(page);
//        baseShowAllResponseDTO.setPageSize(pageSize);
//        baseShowAllResponseDTO.setTotalPages(1);
//        baseShowAllResponseDTO.setTotalElements(reviewList.size());
//        baseShowAllResponseDTO.setLast(true);
//        return new ResponseEntity<>(baseShowAllResponseDTO, HttpStatus.OK);

        return new ResponseEntity<>(reviewServiceImplementation.showReviews(page, pageSize), HttpStatus.OK);

    }
}
