package devcrema.spring_boot_toy.store;

import devcrema.spring_boot_toy.validator.StartEndTime;
import devcrema.spring_boot_toy.validator.StartEndTimeValidatable;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@StartEndTime
public class MakeReservationRequest implements StartEndTimeValidatable {
    LocalDateTime startTime;
    LocalDateTime endTime;
}
