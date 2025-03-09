package portfoilo.commuBoard.entity.system;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import portfoilo.commuBoard.dto.system.CommonCodeDto;
import portfoilo.commuBoard.entity.CommonEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Entity
@Table(name = "SYS_COMM_CODE_T")
public class CommonCode extends CommonEntity {
    /*
    마스터코드 혹은 공통 코드.
    시스템 전반에서 사용할 코드의 체계 정보를 저장합니다. 외부에서 물리적으로 참조하지는 않습니다.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE_NO")
    private Long id;

    @Column(name = "CODE_NM", length = 10, nullable = false)
    @Comment("코드명")
    private String codeNm;

    @Column(name = "CODE_VAL", length = 20, nullable = false)
    @Comment("출력되는 코드값")
    private String codeVal;

    @Column(name = "MEMO", length = 100)
    @Comment("비고")
    private String memo;

    @Column(name = "USE_YN", length = 1, nullable = false)
    @Comment("Y: 사용, N: 미사용")
    @ColumnDefault("'Y'")
    private Character useYn;

    @Column(name = "DEPTH")
    @Comment("코드 깊이. 3단계(최하단) 이상은 생성 차단")
    private int depth;

    // 트리 구조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_CODE_NO")
    @Comment("참조하는 상위 코드")
    private CommonCode parent;

    @OneToMany(mappedBy = "parent")
    private List<CommonCode> children = new ArrayList<>();


    // update

    // res
    public CommonCodeDto toRes() {
        return CommonCodeDto.builder()
                .codeNm(this.codeNm)
                .codeVal(this.codeVal)
                .memo(this.memo)
                .useYn(this.useYn)
                .depth(this.depth)
                .id(this.id)
                .parentId(parent == null ? null : parent.getId())
                .children(children == null ? null : children.stream().map(CommonCode::toRes).toList())
                .build();
    }


}
