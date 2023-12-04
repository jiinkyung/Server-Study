package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.MemberRepository;
import umc.spring.validation.annotation.CheckPage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

@Component
@RequiredArgsConstructor
public class PagesCheckValidator implements ConstraintValidator<CheckPage, Integer> {


    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        Boolean isValid = true;

        if (value < 1) {
            isValid = false;
        }
        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGES_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
