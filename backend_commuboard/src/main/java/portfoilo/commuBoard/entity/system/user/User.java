package portfoilo.commuBoard.entity.system.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import portfoilo.commuBoard.dto.system.UserDto;
import portfoilo.commuBoard.entity.CommonEntity;

import java.time.LocalDate;
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
    TODO: JWT 도입?
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

    @Column(name = "USER_NICK", length = 20, nullable = false)
    @Comment("사용자 닉네임")
    private String userNick;

    @Column(name = "USE_YN", length = 1, nullable = false)
    @Comment("사용여부. N: 로그인 제한.")
    @ColumnDefault("'Y'")
    private Character useYn;

    @Column(name = "ADM_YN", length = 1, nullable = false)
    @Comment("관리자 여부. 개발자 등 마스터계정, 삭제 불가.")
    @ColumnDefault("'N'")
    private Character admYn;

    @Column(name = "EML", length = 100, unique = true)
    @Comment("이메일")
    private String email;

    @Column(name = "LGN_FAIL_CNT")
    @Comment("로그인 실패 횟수")
    private int loginFailCnt;

    @Column(name = "PW_MOD_YMD")
    @Comment("최신 비밀번호 변경일")
    private LocalDate pwModYmd;


    // update
    public void updateLoginFailCnt() {
        loginFailCnt += 1;
    }

    public void initLoginFailCnt() {
        loginFailCnt = 0;
    }


    // UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


    // res
    public UserDto toRes() {
        return UserDto.builder()
                .build();
    }


}
