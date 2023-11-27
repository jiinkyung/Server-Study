package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{regionId}/stores")
    public ApiResponse<StoreResponseDTO.storeResultDTO> addStore(@RequestBody @Valid StoreRequestDTO.storeDTO request,
                                                                 @PathVariable(name = "regionId") Long regionId,
                                                                 @RequestParam(name = "categoryId") Long categoryId){
        Store store = storeCommandService.addStore(regionId, categoryId, request);
        
        return ApiResponse.onSuccess(StoreConverter.toStoreDTO(store));
    }
}
