package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.validation.annotation.ChallengingMission;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "특정 유저의 리뷰 목록 조회 API",description = "특정 유저의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "유저의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistMember @PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page){
        page -= 1;
        Page<Review> storePage = memberQueryService.getReviewList(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.memberReviewPreViewListDTO(storePage));
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "특정 유저가 도전 중인 미션 목록 조회 API",description = "특정 유저가 도전 중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    public ApiResponse<MemberResponseDTO.getMemberChallengingMissionListDTO> getMemberChallengingMissionList(@PathVariable(name = "memberId") Long memberId,
                                                                                                             @CheckPage @RequestParam(name = "page") Integer page){
        Page<MemberMission> memberChallengingMissionPage = memberQueryService.getMemberChallengingMissionList(memberId, page - 1);
        return ApiResponse.onSuccess(MemberMissionConverter.toMemberChallengingMissionListDTO(memberChallengingMissionPage));
    }

}
