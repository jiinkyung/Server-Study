package umc.spring.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.web.dto.MemberResponseDTO;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Validated
public class MissionRestController {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberCommandService memberCommandService;
    private final MemberMissionRepository memberMissionRepository;

    @PostMapping("/addMission")
    @Operation(summary = "특정 가게의 미션을 도전중인 미션에 추가 API (requestParam 이용)", description = "특정 가게의 미션을 도전중인 미션에 추가하는 API 입니다.")
    public ApiResponse<MemberResponseDTO.addMemberMissionResultDTO> addMemberMission(@RequestParam Long memberId,
                                                                                     @RequestParam Long missionId) {

        // request로 받은 사용자가 존재하지 않으면
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent() == false){
            throw new GeneralException(ErrorStatus.MEMBER_NOT_FOUND);
        }

        // request로 받은 미션이 존재하지 않으면
        Optional<Mission> mission = missionRepository.findById(missionId);
        if(mission.isPresent() == false){
            throw new GeneralException(ErrorStatus.MISSION_NOT_FOUND);
        }

        // 이미 도전중인 미션이면
        if (memberMissionRepository.existsByMissionIdAndMemberId(missionId, memberId) == true) {
            throw new GeneralException(ErrorStatus.MISSION_ALREADY_CHALLENGING);
        }

        MemberMission memberMission = memberCommandService.memberAddMission(memberId, missionId);

        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionDTO(memberMission));
    }
}
