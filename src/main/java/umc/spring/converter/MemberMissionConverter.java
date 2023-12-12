package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MemberResponseDTO.addMemberMissionResultDTO toMemberMissionDTO (MemberMission memberMission){
        return MemberResponseDTO.addMemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toEntity(Long memberId, Long missionId) {
        return MemberMission.builder()
                .member(Member.builder().id(memberId).build())
                .mission(Mission.builder().id(missionId).build())
                .build();
    }

    public static MemberResponseDTO.getMemberChallengingMissionDTO toMemberChallengingMissionDTO(MemberMission memberMission){
        return MemberResponseDTO.getMemberChallengingMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .storeName(memberMission.getMission().getStore().getName())
                .missionId(memberMission.getMission().getId())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .point(memberMission.getMission().getPoint())
                .build();
    }

    public static MemberResponseDTO.getMemberChallengingMissionListDTO toMemberChallengingMissionListDTO(Page<MemberMission> memberChallengingList){
        List<MemberResponseDTO.getMemberChallengingMissionDTO> memberChallengingMissionList = memberChallengingList.stream()
                .map(MemberMissionConverter::toMemberChallengingMissionDTO).collect(Collectors.toList());

        return MemberResponseDTO.getMemberChallengingMissionListDTO.builder()
                .isLast(memberChallengingList.isLast())
                .isFirst(memberChallengingList.isFirst())
                .totalPage(memberChallengingList.getTotalPages())
                .totalElements(memberChallengingList.getTotalElements())
                .listSize(memberChallengingMissionList.size())
                .memberChallengingMissionList(memberChallengingMissionList)
                .build();
    }

}
