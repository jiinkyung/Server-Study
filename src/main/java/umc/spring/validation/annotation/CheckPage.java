package umc.spring.validation.annotation;

import umc.spring.validation.validator.PagesCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PagesCheckValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {

    String message() default "페이지는 1 이상의 값을 입력해주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
