package devcrema.spring_boot_toy.store;

import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.test_fixture.StoreFixtureGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomTestConfiguration.class)
@ActiveProfiles(profiles = "test")
@Transactional
@Slf4j
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreFixtureGenerator storeFixtureGenerator;

    @Test
    @DisplayName("음식점 목록을 페이징해서 불러옴")
    public void loadAllStoresWithPaging(){
        //given
        int page = 0;
        int size = 4;
        Pageable pageable = PageRequest.of(page, size);
        List<Store> storeList = storeFixtureGenerator.generateStores(6);
        //when
        List<Store> stores = storeRepository.findAllBy(pageable);
        //then
        assertThat(stores)
                .hasSize(size)
                .isEqualTo(storeList.subList(0, size));
    }

}