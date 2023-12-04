package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionResponseDTO.storeMissionPreViewDTO storeMissionPreviewDTO(Mission mission){
        return MissionResponseDTO.storeMissionPreViewDTO.builder()
                .missionSpec(mission.getMissionSpec())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResponseDTO.storeMissionPreViewListDTO storeMissionPreviewListDTO(Page<Mission> storeMissionList) {
        List<MissionResponseDTO.storeMissionPreViewDTO> storeMissionPreviewDTOList = storeMissionList.stream()
                .map(MissionConverter::storeMissionPreviewDTO).collect(Collectors.toList());

        return MissionResponseDTO.storeMissionPreViewListDTO.builder()
                .isLast(storeMissionList.isLast())
                .isFirst(storeMissionList.isFirst())
                .totalPage(storeMissionList.getTotalPages())
                .totalElements(storeMissionList.getTotalElements())
                .listSize(storeMissionPreviewDTOList.size())
                .storeMissionList(storeMissionPreviewDTOList)
                .build();
    }
}
