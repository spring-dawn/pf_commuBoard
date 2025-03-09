package portfoilo.commuBoard.entity.system;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import portfoilo.commuBoard.dto.system.MenuDto;
import portfoilo.commuBoard.entity.CommonEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert // 디폴트값 자동 세팅
@Entity
@Table(name = "SYS_MENU_T")
public class Menu extends CommonEntity {
    /*
    권한 제어가 필요한 메뉴.
    탭, 모달 등 고유 url 이 없는 기능은 프론트에서 관리.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_NO")
    private Long id;

    @Column(name = "MENU_URL", nullable = false, unique = true, length = 100)
    @Comment("메뉴 주소. e.g.) /system/user..")
    private String url;

    @Column(name = "MENU_NM", nullable = false, length = 20)
    @Comment("메뉴명")
    private String menuNm;

    @Column(name = "SORT_ORDER", nullable = false)
    @Comment("정렬순서. 같은 깊이(depth)에서 숫자가 작을수록 우선")
    private int order;

    @Column(name = "DEPTH")
    @Comment("메뉴의 현재 깊이. 0부터 시작.")
    private int depth;

    @Column(name = "USE_YN", length = 1, nullable = false)
    @Comment("사용여부. Y: 사용, N: 미사용(출력x)")
    @ColumnDefault("'Y'")
    private Character useYn;

    // 트리 구조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_MENU_NO")
    @Comment("참조하는 상위 메뉴")
    private Menu parent;    // 상위 메뉴

    @OneToMany(mappedBy = "parent")
    private List<Menu> children = new ArrayList<>(); // 하위 메뉴들


    // update
    public void addChildren(List<Menu> children) {
        this.children = children;
    }


    // res
    public MenuDto toRes() {
        return MenuDto.builder()
                .id(id)
                .url(url)
                .menuNm(menuNm)
                .order(order)
                .depth(depth)
                .useYn(useYn)
                .parentId(parent == null ? null : parent.getId())
                .children(children == null? null : children.stream().map(Menu::toRes).toList())
                .build();
    }

}
