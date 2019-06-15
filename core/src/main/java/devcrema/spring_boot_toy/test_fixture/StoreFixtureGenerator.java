package devcrema.spring_boot_toy.test_fixture;

import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.store.Store;
import devcrema.spring_boot_toy.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreFixtureGenerator {

    public static final String NAME = "홍대음식점";
    public static final LocalTime OPENING_TIME = LocalTime.of(10, 0);
    public static final LocalTime CLOSING_TIME = LocalTime.of(20, 0);

    private final StoreRepository storeRepository;

    public Store generateStore(String name) {
        return storeRepository.findByName(name).orElseGet(()
                -> storeRepository.save(Store.builder()
                .name(name)
                .openingTime(OPENING_TIME)
                .closingTime(CLOSING_TIME)
                .build()));
    }

    public List<Store> generateStores(int count) {
        List<Store> stores = new ArrayList<>();
        for(int i =0; i < count; i++){
            stores.add(generateStore("음식점"+i));
        }
        return stores;
    }

    public void addChef(Store store, Chef chef){

        store.addChef(chef);
    }
}