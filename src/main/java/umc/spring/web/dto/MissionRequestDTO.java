package umc.spring.web.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class MissionDTO {
        String missionSpec;
        LocalDate deadline;
        Integer point;
    }
}
