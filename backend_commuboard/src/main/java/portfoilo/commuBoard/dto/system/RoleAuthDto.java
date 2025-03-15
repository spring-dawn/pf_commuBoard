package portfoilo.commuBoard.dto.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RoleAuthDto {
    // TODO: pf_eams 에서는 record 로 선언했는데, 설계상 될지 모르겠다... 일단 시도.
    private Long id;
    private Long roleId;
    private Long authId;
    private Character authType;
    private Long menuId;
    private String menuUrl;

}
