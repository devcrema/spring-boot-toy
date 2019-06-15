package devcrema.spring_boot_toy.store;

import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MakeReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation makeReservation(User user, Store store, Chef chef, LocalDateTime startTime, LocalDateTime endTime) {
        if(!store.chefExist(chef)) throw new ChefNotExistException();
        if(!store.validOpenTime(startTime, endTime)) throw new StoreIsNotOpenException();
        Reservation reservation = Reservation.builder()
                .chef(chef)
                .user(user)
                .store(store)
                .startTime(startTime)
                .endTime(endTime)
                .build();
        return reservationRepository.save(reservation);
    }
}
