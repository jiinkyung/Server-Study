package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMissionIdAndMemberId(Long missionId, Long memberId);

    Page<MemberMission> findAllByMemberIdAndStatus(Long memberId, MissionStatus missionStatus, PageRequest pageRequest);
    //Page<MemberMission> findMemberChallengingMission(Member member, MissionStatus missionStatus, PageRequest pageRequest);

}
