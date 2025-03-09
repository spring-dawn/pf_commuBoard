package portfoilo.commuBoard.dto.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import portfoilo.commuBoard.dto.BaseDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String roleNm;
    private String memo;
    private int order;
    private Character useYn;
    private List<RoleAuthDto> roleAuthList;
    BaseDto baseDto;

}
