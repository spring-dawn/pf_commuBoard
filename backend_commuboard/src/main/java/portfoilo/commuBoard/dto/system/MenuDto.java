package portfoilo.commuBoard.dto.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MenuDto {
    private Long id;
    private String url;
    private String menuNm;
    private int order;
    private int depth;
    private Character useYn;
    private Long parentId;
    private List<MenuDto> children;
}
