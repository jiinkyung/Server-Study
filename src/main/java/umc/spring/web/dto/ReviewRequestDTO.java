package umc.spring.web.dto;

import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewDTO{
        String title;
        String description;
        Float score;
    }
}
