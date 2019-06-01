package devcrema.spring_boot_toy.test_fixture;

import devcrema.spring_boot_toy.store.Store;
import devcrema.spring_boot_toy.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreFixtureGenerator {

    public static final String NAME = "홍대음식점";

    private final StoreRepository storeRepository;

    public Store generateStore(String name) {
        return storeRepository.findByName(name).orElseGet(()
                -> storeRepository.save(Store.builder()
                .name(name)
                .build()));
    }

    public List<Store> generateStores(int count) {
        List<Store> stores = new ArrayList<>();
        for(int i =0; i < count; i++){
            stores.add(generateStore("음식점"+i));
        }
        return stores;
    }

}