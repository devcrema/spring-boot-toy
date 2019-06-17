package devcrema.spring_boot_toy.store;

import devcrema.spring_boot_toy.CustomTestConfiguration;
import devcrema.spring_boot_toy.ReservationTime;
import devcrema.spring_boot_toy.chef.Chef;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    StoreRepository storeRepository;
    @Autowired
    ChefFixtureGenerator chefFixtureGenerator;
    @Autowired
    ReservationRepository reservationRepository;

    private User user;
    private Store store;
    private Chef chef;

    @BeforeEach
    public void setUp(){
        user = userFixtureGenerator.generateUser();
        store = storeFixtureGenerator.generateStore(StoreFixtureGenerator.NAME);
        chef = chefFixtureGenerator.generateChef();
        if(store.addChef(chef)) storeRepository.save(store);
    }

    @Test
    @DisplayName("예약할 수 있어야함")
    public void mustBeAbleToMakeReservation(){
        //given
        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDateTime startTime = LocalDateTime.of(now, LocalTime.of(10, 0));
        LocalDateTime endTime = LocalDateTime.of(now, LocalTime.of(12, 0));
        ReservationTime reservationTime = new ReservationTime() {
            @Override
            public LocalDateTime getStartTime() {
                return startTime;
            }

            @Override
            public LocalDateTime getEndTime() {
                return endTime;
            }
        };
        //when
        Reservation reservation = makeReservationService.makeReservation(user, store, chef, reservationTime);
        //then
        assertThat(reservationRepository.findById(reservation.getId())).isPresent();
    }

    @Test
    @DisplayName("요리사가 없으면 에러 발생")
    public void givenNoChefThenErrorOccur(){
        //given
        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDateTime startTime = LocalDateTime.of(now, LocalTime.of(10, 0));
        LocalDateTime endTime = LocalDateTime.of(now, LocalTime.of(12, 0));
        ReservationTime reservationTime = new ReservationTime() {
            @Override
            public LocalDateTime getStartTime() {
                return startTime;
            }

            @Override
            public LocalDateTime getEndTime() {
                return endTime;
            }
        };
        Chef anotherChef = chefFixtureGenerator.generateChef("newChef@test.test", "newChef");
        //when, then
        assertThrows(ChefNotExistException.class, ()->makeReservationService.makeReservation(user, store, anotherChef, reservationTime));
    }

    @Test
    @DisplayName("음식점 오픈시간이 아니면 에러 발생")
    public void givenNoOpeningTimeThenErrorOccur(){
        //given
        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDateTime startTime = LocalDateTime.of(now, store.getClosingTime().minusHours(2));
        LocalDateTime endTime = LocalDateTime.of(now, store.getClosingTime().plusMinutes(30));
        ReservationTime reservationTime = new ReservationTime() {
            @Override
            public LocalDateTime getStartTime() {
                return startTime;
            }

            @Override
            public LocalDateTime getEndTime() {
                return endTime;
            }
        };
        //when, then
        assertThrows(StoreIsNotOpenException.class, ()->makeReservationService.makeReservation(user, store, chef, reservationTime));
    }

}