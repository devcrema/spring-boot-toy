package devcrema.spring_boot_toy.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartEndTimeValidator implements ConstraintValidator<StartEndTime, StartEndTimeValidatable> {

    @Override
    public boolean isValid(StartEndTimeValidatable value, ConstraintValidatorContext context) {
        if(value == null || value.getStartTime() == null || value.getEndTime() == null) return false;

        return !value.getStartTime().isAfter(value.getEndTime());
    }
}
