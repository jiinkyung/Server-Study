package umc.spring.service.StoreService;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store addStore(Long regionId, Long categoryId, StoreRequestDTO.storeDTO request);
}
