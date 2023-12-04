package umc.spring.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.spring.domain.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =10)
    private String name;

    @Column(nullable = false, length = 40)
    private String address;

    @ColumnDefault("0")
    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    public void setFoodCategory(FoodCategory foodCategory){
        if(this.foodCategory != null)
            this.foodCategory.getStoreList().remove(this);
        this.foodCategory = foodCategory;
        foodCategory.getStoreList().add(this);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    public void setRegion(Region region){
        if(this.region != null)
            this.region.getStoreList().remove(this);
        this.region = region;
        region.getStoreList().add(this);
    }

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
