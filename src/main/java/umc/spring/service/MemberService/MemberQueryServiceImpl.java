package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }
    @Override
    public Page<Review> getReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        Page<Review> memberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 3));
        return memberPage;
    }

    @Override
    public Page<MemberMission> getMemberChallengingMissionList(Long memberId, Integer page){
        Page<MemberMission> memberMission = memberMissionRepository.findAllByMemberIdAndStatus(memberId, MissionStatus.CHALLENGING, PageRequest.of(page, 3));

        if (page + 1 > memberMission.getTotalPages()){
            throw new GeneralException(ErrorStatus.PAGES_NOT_FOUND);
        }
        return memberMission;
    }
}
