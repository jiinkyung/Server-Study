package umc.spring.web.dto;

import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    public static class storeDTO{
        String name;
        String address;
    }
}
