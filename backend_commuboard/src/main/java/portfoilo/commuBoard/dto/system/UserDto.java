package portfoilo.commuBoard.dto.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import portfoilo.commuBoard.dto.BaseDto;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    /*
    로그인 사용자 dto.
     */

    private Long id;
    private String userId;
    private String userPw;
    private String salt;
    private String userNick;
    private Character useYn;
    private Character admYn;
    private String email;
    private int loginFailCnt;
    private LocalDate pwModYmd;

    // 공통 요소 컴포지션
    BaseDto baseDto;

}
