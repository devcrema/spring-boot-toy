package devcrema.spring_boot_toy.validation;

import devcrema.spring_boot_toy.ReservationTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

//TODO 이건 테스트코드가 필요할듯
public class ReservationTimeValidator implements ConstraintValidator<ValidReservationTime, ReservationTime> {
    @Override
    public boolean isValid(ReservationTime value, ConstraintValidatorContext context) {
        if(value == null || value.getStartTime() == null || value.getEndTime() == null) return false;
        if(value.getStartTime().isAfter(value.getEndTime())) return false;
        LocalDateTime startTimeAfterMinimumTime = value.getStartTime().plusHours(1);

        return startTimeAfterMinimumTime.isBefore(value.getEndTime()) || startTimeAfterMinimumTime.isEqual(value.getEndTime());
    }

}
