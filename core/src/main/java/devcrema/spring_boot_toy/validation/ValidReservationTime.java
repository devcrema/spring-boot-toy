package devcrema.spring_boot_toy.validation;

import devcrema.spring_boot_toy.validation.ReservationTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ReservationTimeValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface ValidReservationTime {
    String message() default "시간이 올바르지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
