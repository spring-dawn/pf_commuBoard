package portfoilo.commuBoard.dto.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CommonCodeDto {
    private Long id;
    private String codeNm;
    private String codeVal;
    private String memo;
    private Character useYn;
    private int depth;
    private Long parentId;
    private List<CommonCodeDto> children;

}
