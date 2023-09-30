package dev.mohibullah.pizzaappbackendjava.controllers;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ReviewRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ReviewResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @PostMapping("addReview")
    public ResponseEntity<ReviewResponseDTO> createReview(@Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
//        return new ResponseEntity<>(controllerService.createCategory(CategoryRequestDTO), HttpStatus.CREATED);

        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
        reviewResponseDTO.setId(1);
        reviewResponseDTO.setRating(reviewRequestDTO.getRating());
        reviewResponseDTO.setDescription(reviewRequestDTO.getDescription());
        reviewResponseDTO.setCreatedAt(LocalDateTime.now());
        reviewResponseDTO.setUpdatedAt(LocalDateTime.now());
        System.out.println(reviewResponseDTO);
        return new ResponseEntity<>(reviewResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("showReviews")
    public ResponseEntity<BaseResponseDTO<ReviewResponseDTO>> showReviews(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<ReviewResponseDTO> reviewList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
            reviewResponseDTO.setId(i + 1);
            reviewResponseDTO.setRating(3.5);
            reviewResponseDTO.setDescription("Late delivery");
            reviewResponseDTO.setCreatedAt(LocalDateTime.now());
            reviewResponseDTO.setUpdatedAt(LocalDateTime.now());
            reviewList.add(reviewResponseDTO);
        }
        System.out.println(reviewList);



        BaseResponseDTO<ReviewResponseDTO> baseResponseDTO = new BaseResponseDTO<>();
        baseResponseDTO.setContent(reviewList);
        baseResponseDTO.setPageNo(pageNo);
        baseResponseDTO.setPageSize(pageSize);
        baseResponseDTO.setTotalPages(1);
        baseResponseDTO.setTotalElements(reviewList.size());
        baseResponseDTO.setLast(true);
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);
    }
}
