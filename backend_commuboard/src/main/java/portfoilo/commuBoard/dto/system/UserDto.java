package portfoilo.commuBoard.dto.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import portfoilo.commuBoard.dto.BaseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    /*
    로그인 사용자 dto
    toRes 에 쓰일 단순 조회용은 NoArgsConstructor 없어도 됨
     */
    private Long id;
    private String userId;
    private String userNick;
    private Character useYn;
    private Character admYn;
    private String email;
    private int loginFailCnt;
    private int reportCnt;
    private LocalDate pwModYmd;
    private LocalDateTime quitDtm;
    BaseDto baseDto; // 공통 요소 컴포지션

}
