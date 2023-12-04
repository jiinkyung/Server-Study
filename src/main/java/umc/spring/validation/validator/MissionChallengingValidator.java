package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerErrorException;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.validation.annotation.ChallengingMission;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.web.dto.MemberRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

@Component
@RequiredArgsConstructor
public class MissionChallengingValidator implements ConstraintValidator<ChallengingMission, MemberRequestDTO.addMemberMission> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(ChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberRequestDTO.addMemberMission request, ConstraintValidatorContext context) {

        Long missionId = request.getMissionId();
        Long memberId = request.getMemberId();

        boolean isUnique = memberMissionRepository.existsByMissionIdAndMemberId(missionId, memberId);

        if (isUnique) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGING.toString()).addConstraintViolation();
        }

        return !isUnique;


    }
}
