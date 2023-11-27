package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.ReviewDTO request){
        return Review.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .score(request.getScore())
                .build();
    }

    public static ReviewResponseDTO.ReviewResultDTO toReviewDTO(Review review){
        return ReviewResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
