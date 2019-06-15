package devcrema.spring_boot_toy.validator;

import java.time.LocalDateTime;

public interface StartEndTimeValidatable {
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
}
