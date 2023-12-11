package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {

//    public static MemberMission toEntity(MemberRequestDTO.addMemberMission request){
//        MissionStatus status = null;
//
//        switch (request.getStatus()) {
//            case 1:
//                status = MissionStatus.CHALLENGING;
//                break;
//
//            case 2:
//                status = MissionStatus.COMPLETE;
//                break;
//        }
//        return MemberMission.builder()
//                .status(status)
//                .build();
//    }

    public static MemberMission toEntity(MemberRequestDTO.addMemberMission request){
        return MemberMission.builder()
                .member(Member.builder().id(request.getMemberId()).build())
                .mission(Mission.builder().id(request.getMissionId()).build())
                .build();
    }
    public static MemberResponseDTO.addMemberMissionResultDTO toMemberMissionDTO (MemberMission memberMission){
        return MemberResponseDTO.addMemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toEntity2(Long memberId, Long missionId) {
        return MemberMission.builder()
                .member(Member.builder().id(memberId).build())
                .mission(Mission.builder().id(missionId).build())
                .build();
    }
}
