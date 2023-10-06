package dev.mohibullah.pizzaappbackendjava.services.implementations;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ReviewRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ReviewResponseDTO;
import dev.mohibullah.pizzaappbackendjava.exceptions.EmptyItemsListException;
import dev.mohibullah.pizzaappbackendjava.models.Review;
import dev.mohibullah.pizzaappbackendjava.repositories.ReviewRepository;
import dev.mohibullah.pizzaappbackendjava.services.Interfaces.ReviewServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplementation implements ReviewServiceInterface {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO) {

        Review review = reviewRepository.save(mapToEntity(reviewRequestDTO));

        return mapToResponseDto(review);
    }

    @Override
    public BaseShowAllResponseDTO<ReviewResponseDTO> showReviews(int page, int pageSize) {
        if (pageSize == 0) {
            List<Review> allReviews = reviewRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            if (allReviews.isEmpty()) {
                throw new EmptyItemsListException("No Reviews in database");
            }

            List<ReviewResponseDTO> content = allReviews.stream()
                    .map(review -> mapToResponseDto(review))
                    .toList();

            BaseShowAllResponseDTO<ReviewResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(0);
            response.setPageSize(allReviews.size());
            response.setTotalElements(allReviews.size());
            response.setTotalPages(1);
            response.setLast(true);

            return response;
        } else {
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "id"));
            Page<Review> reviews = reviewRepository.findAll(pageable);

            if (reviews.getTotalElements() == 0) {
                throw new EmptyItemsListException("No Reviews in database");
            }

            if (reviews.isEmpty()) {
                throw new EmptyItemsListException("No Reviews in page: " + reviews.getNumber());
            }

            List<Review> listOfReviews = reviews.getContent();
            List<ReviewResponseDTO> content = listOfReviews.stream()
                    .map(review -> mapToResponseDto(review))
                    .toList();

            BaseShowAllResponseDTO<ReviewResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(reviews.getNumber());
            response.setPageSize(reviews.getSize());
            response.setTotalElements(reviews.getTotalElements());
            response.setTotalPages(reviews.getTotalPages());
            response.setLast(reviews.isLast());

            return response;
        }
    }


    private ReviewResponseDTO mapToResponseDto(Review review) {
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
        reviewResponseDTO.setId(review.getId());
        reviewResponseDTO.setRating(review.getRating());
        reviewResponseDTO.setDescription(review.getDescription());
        reviewResponseDTO.setCreatedAt(LocalDateTime.now());
        reviewResponseDTO.setUpdatedAt(LocalDateTime.now());

        return reviewResponseDTO;
    }

    private Review mapToEntity(ReviewRequestDTO reviewRequestDTO) {
        Review review = new Review();
        review.setRating(reviewRequestDTO.getRating());
        review.setDescription(reviewRequestDTO.getDescription());
        return review;
    }
}
