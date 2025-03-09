package portfoilo.commuBoard.entity.system;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import portfoilo.commuBoard.dto.BaseDto;
import portfoilo.commuBoard.dto.system.UserDto;
import portfoilo.commuBoard.entity.CommonEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Entity
@Table(name = "DIM_USER_T")
public class User extends CommonEntity implements UserDetails {

    /*
    로그인 사용자. Spring security 로 관리.
    TODO: JWT 도입
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO")
    private Long id;

    @Column(name = "USER_ID", length = 20, unique = true, nullable = false)
    @Comment("사용자 ID")
    private String userId;

    @Column(name = "USER_PW", length = 200, nullable = false)
    @Comment("사용자 비밀번호")
    private String userPw;

    @Column(name = "SALT", length = 30, nullable = false)
    @Comment("암호화 솔트")
    private String salt;

    @Column(name = "USER_NICK", length = 15)
    @Comment("사용자 닉네임")
    private String userNick;

    @Column(name = "USE_YN", length = 1, nullable = false)
    @Comment("사용여부. N: 로그인 제한")
    @ColumnDefault("'Y'")
    private Character useYn;

    @Column(name = "ADM_YN", length = 1, nullable = false)
    @Comment("관리자 여부. 개발자 등 마스터계정, 삭제 불가")
    @ColumnDefault("'N'")
    private Character admYn;

    @Column(name = "EML", length = 100, unique = true, nullable = false)
    @Comment("이메일")
    private String email;

    @Column(name = "LGN_FAIL_CNT")
    @Comment("로그인 실패 횟수")
    private int loginFailCnt;

    @Column(name = "PW_MOD_YMD")
    @Comment("최신 비밀번호 변경일")
    private LocalDate pwModYmd;

    @Column(name = "QUIT_DTM")
    @Comment("탈퇴일시")
    private LocalDateTime quitDtm;

    @Column(name = "REPORT_CNT")
    @Comment("신고 횟수. 누적되면 이용 정지")
    private int reportCnt;

    /*
      User : Role = N : 1 단방향 참조.
      연관 필드에는 반드시 @JsonIgnore 등 순환참조 방어 어노테이션을 붙입니다
      @ManyToOne 의 기본 fetch 전략은 EAGER 이므로 LAZY 를 명시합니다.
      양방향 매핑은 가능한 한 피하고, 필요한 만큼만 연관 짓습니다.
   */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_NO")
    @Comment("사용 중인 역할 번호")
    private Role role;


    // update
    public void updateLoginFailCnt() {
        loginFailCnt++;
    }
    public void initLoginFailCnt() {
        loginFailCnt = 0;
    }


    // UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.getRole().getRoleNm()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return userPw;
    }

    /**
     * 커뮤니티에선 이름을 받지 않으므로 닉네임을 반환
     * @return
     */
    @Override
    public String getUsername() {
        return userNick;
    }

    @Override
    public boolean isAccountNonLocked() {
        return loginFailCnt <= 5;
    }

    @Override
    public boolean isEnabled() {
        return useYn.equals('Y');
    }


    // res
    public UserDto toRes() {
        return UserDto.builder()
                .id(id)
                .userId(userId)
                .userNick(userNick)
                .useYn(useYn)
                .admYn(admYn)
                .email(email)
                .loginFailCnt(loginFailCnt)
                .reportCnt(reportCnt)
                .pwModYmd(pwModYmd)
                .quitDtm(quitDtm)
                .baseDto(BaseDto.from(this))
                .build();
    }


}
