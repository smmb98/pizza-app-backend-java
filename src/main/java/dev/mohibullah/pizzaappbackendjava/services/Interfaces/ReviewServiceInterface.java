package dev.mohibullah.pizzaappbackendjava.services.Interfaces;

import dev.mohibullah.pizzaappbackendjava.dtos.request.ReviewRequestDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.BaseShowAllResponseDTO;
import dev.mohibullah.pizzaappbackendjava.dtos.response.ReviewResponseDTO;

public interface ReviewServiceInterface {
    ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO);

    BaseShowAllResponseDTO<ReviewResponseDTO> showReviews(int page, int pageSize);

}
