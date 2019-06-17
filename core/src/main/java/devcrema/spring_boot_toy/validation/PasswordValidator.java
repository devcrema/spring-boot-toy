package devcrema.spring_boot_toy.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isBlank(value)) return false;

        return value.length() >= 6 && value.length() <= 30;
    }
}
