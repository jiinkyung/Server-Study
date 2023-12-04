package umc.spring.domain;

import lombok.*;
import umc.spring.domain.base.BaseEntity;
import umc.spring.domain.mapping.MemberMission;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false, length = 50)
    private String missionSpec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public void setStore(Store store){
        if (this.store != null)
            this.store.getMissionList().remove(this);
        this.store = store;
        store.getMissionList().add(this);

    }

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
