package umc.spring.domain.mapping;

import lombok.*;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.base.BaseEntity;
import umc.spring.domain.enums.MissionStatus;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    public void setStatus(MissionStatus status){
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
//        if (this.member != null)
//            this.member.getMemberMissionList().remove(this);
//        this.member = member;
//        member.getMemberMissionList().add(this);
        this.member = member;

    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
    public void setMission(Mission mission) {
//        if (this.mission != null)
//            this.mission.getMemberMissionList().remove(this);
//        this.mission = mission;
//        mission.getMemberMissionList().add(this);
        this.mission = mission;

    }

}
