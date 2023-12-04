package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Validated
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/regions/{regionId}")
    public ApiResponse<StoreResponseDTO.storeResultDTO> addStore(@RequestBody @Valid StoreRequestDTO.storeDTO request,
                                                                 @PathVariable(name = "regionId") Long regionId,
                                                                 @RequestParam(name = "categoryId") Long categoryId){
        Store store = storeCommandService.addStore(regionId, categoryId, request);
        
        return ApiResponse.onSuccess(StoreConverter.toStoreDTO(store));
    }

    @PostMapping("{storeId}/missions")
    @Operation(summary = "특정 가게에 미션 추가 API",description = "특정 가게에 미션을 추가하는 API입니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionResultDTO> addMission(@RequestBody @Valid MissionRequestDTO.MissionDTO request,
                                                                       @ExistStore @PathVariable(name = "storeId") Long storeId) {
        Mission mission = storeCommandService.addMission(storeId, request);

        return ApiResponse.onSuccess(MissionConverter.toMissionDTO(mission));
    }

    @GetMapping("{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 조회 API",description = "특정 가게의 미션을 조회하는 API입니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponseDTO.storeMissionPreViewListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                                     @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Mission> storeMissionPage = storeQueryService.getMisisonList(storeId, page-1);
        return ApiResponse.onSuccess(MissionConverter.storeMissionPreviewListDTO(storeMissionPage));
    }
}
