package devcrema.spring_boot_toy;

import java.time.LocalDateTime;

public interface ReservationTime {
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
}
