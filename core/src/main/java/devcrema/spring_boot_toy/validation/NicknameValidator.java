package devcrema.spring_boot_toy.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NicknameValidator implements ConstraintValidator<ValidNickname, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isBlank(value)) return false;

        return value.length() >= 1 && value.length() <= 10;
    }
}
