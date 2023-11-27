package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{

    public final ReviewRepository reviewRepository;
    public final MemberRepository memberRepository;
    public final StoreRepository storeRepository;
    @Override
    @Transactional
    public Review createReview(Long memberId, Long storeId, ReviewRequestDTO.ReviewDTO request){
        Review review = ReviewConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }
}
