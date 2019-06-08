package devcrema.spring_boot_toy.reservation;

import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.store.Store;
import devcrema.spring_boot_toy.test_fixture.ChefFixtureGenerator;
import devcrema.spring_boot_toy.test_fixture.StoreFixtureGenerator;
import devcrema.spring_boot_toy.test_fixture.UserFixtureGenerator;
import devcrema.spring_boot_toy.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomTestConfiguration.class)
@ActiveProfiles(profiles = "test")
@Transactional
class MakeReservationServiceTest {

    @Autowired
    MakeReservationService makeReservationService;
    @Autowired
    UserFixtureGenerator userFixtureGenerator;
    @Autowired
    StoreFixtureGenerator storeFixtureGenerator;
    @Autowired
    ChefFixtureGenerator chefFixtureGenerator;

    private User user;
    private Store store;
    private Chef chef;

    @BeforeEach
    public void setUp(){
        user = userFixtureGenerator.generateUser();
        store = storeFixtureGenerator.generateStore(StoreFixtureGenerator.NAME);
        chef = chefFixtureGenerator.generateChef();
    }



    @Test
    @DisplayName("예약할 수 있어야함")
    public void mustBeAbleToMakeReservation(){
        //TODO localDateTime startTime, localDateTime endTime 으로 구성된 메서드 만들기
        //given

        //when

        //then

    }
}