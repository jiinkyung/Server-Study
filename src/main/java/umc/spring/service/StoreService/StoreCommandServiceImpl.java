package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    @Override
    @Transactional
    public Store addStore(Long regionId, Long categoryId, StoreRequestDTO.storeDTO request){
        Store store = StoreConverter.toStore(request);

        store.setRegion(regionRepository.findById(regionId).get());
        store.setFoodCategory(foodCategoryRepository.findById(categoryId).get());

        return storeRepository.save(store);
    }


}
