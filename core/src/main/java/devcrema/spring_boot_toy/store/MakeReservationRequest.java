package devcrema.spring_boot_toy.store;

import devcrema.spring_boot_toy.ReservationTime;
import devcrema.spring_boot_toy.validation.ValidReservationTime;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ValidReservationTime
public class MakeReservationRequest implements ReservationTime {
    LocalDateTime startTime;
    LocalDateTime endTime;
}
