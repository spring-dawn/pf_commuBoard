package portfoilo.commuBoard.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import portfoilo.commuBoard.dto.BaseDto;
import portfoilo.commuBoard.dto.system.RoleDto;
import portfoilo.commuBoard.entity.CommonEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Entity
@Table(name = "SYS_ROLE_T")
public class Role extends CommonEntity {
    /*
    사용자 등급
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_NO")
    private Long id;

    @Column(name = "ROLE_NM", length = 10)
    @Comment("사용자 역할(등급)명")
    private String roleNm;

    @Column(name = "MEMO", length = 50)
    @Comment("역할 내용")
    private String memo;

    @Column(name = "SORT_ORDER")
    @Comment("정렬 순서")
    private int order;

    @Column(name = "USE_YN", length = 1, nullable = false)
    @Comment("사용여부. Y: 사용, N: 미사용")
    @ColumnDefault("'Y'")
    private Character useYn;

    /*
    연관관계
    Role 이 삭제되는 경우 RoleAuth 도 모두 삭제, Auth 는 불변 기준이므로 영향 X
     */
    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<RoleAuth> roleAuthList = new ArrayList<>();


    // update
    public void addAuthList(List<RoleAuth> roleAuthList) {
        this.roleAuthList = roleAuthList;
    }


    // res
    public RoleDto toRes() {
        return RoleDto.builder()
                .id(id)
                .roleNm(roleNm)
                .memo(memo)
                .order(order)
                .useYn(useYn)
//                .roleAuthList(roleAuthList == null ? null : roleAuthList.stream().map(RoleAuth::toRes).toList())
                .baseDto(BaseDto.from(this))
                .build();
    }

}
