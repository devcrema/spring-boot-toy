package devcrema.spring_boot_toy.store;

import devcrema.spring_boot_toy.ReservationTime;
import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MakeReservationService {

    private final ReservationRepository reservationRepository;
    //TODO 메서드 파라미터를 줄일 방법이 없을까?
    public Reservation makeReservation(User user, Store store, Chef chef, ReservationTime startEndTime) {
        if(!store.chefExist(chef)) throw new ChefNotExistException();
        if(!store.validOpenTime(startEndTime.getStartTime(), startEndTime.getEndTime())) throw new StoreIsNotOpenException();
        Reservation reservation = Reservation.builder()
                .chef(chef)
                .user(user)
                .store(store)
                .startTime(startEndTime.getStartTime())
                .endTime(startEndTime.getEndTime())
                .build();
        return reservationRepository.save(reservation);
    }
}
