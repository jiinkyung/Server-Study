package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewResultDTO> createReview(@RequestBody @Valid ReviewRequestDTO.ReviewDTO request,
                                                                       @PathVariable(name = "storeId") Long storeId,
                                                                       @RequestParam(name = "memberId") Long memberId){
        Review review = reviewCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(ReviewConverter.toReviewDTO(review));
    }
}
