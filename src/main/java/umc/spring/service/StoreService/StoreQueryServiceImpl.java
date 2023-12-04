package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    public final StoreRepository storeRepository;
    public final MissionRepository missionRepository;
    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Page<Mission> getMisisonList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        Page<Mission> storeMissionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 3));

        return storeMissionPage;
    }
}
