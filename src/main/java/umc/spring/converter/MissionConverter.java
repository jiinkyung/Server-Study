package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static Mission toEntity(MissionRequestDTO.MissionDTO request){
        return Mission.builder()
                .missionSpec(request.getMissionSpec())
                .point(request.getPoint())
                .deadline(request.getDeadline())
                .build();
    }

    public static MissionResponseDTO.MissionResultDTO toMissionDTO(Mission mission){
        return MissionResponseDTO.MissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
