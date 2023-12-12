package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JoinResultDTO {
        Long memberId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class addMemberMissionResultDTO {
        Long memberMissionId;
        LocalDateTime createAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getMemberChallengingMissionDTO {
        Long memberMissionId;
        LocalDateTime createdAt;
        String storeName;
        Long missionId;
        String missionSpec;
        Integer point;


    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getMemberChallengingMissionListDTO{
        List<MemberResponseDTO.getMemberChallengingMissionDTO> memberChallengingMissionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

}
