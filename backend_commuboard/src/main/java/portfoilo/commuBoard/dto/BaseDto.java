package portfoilo.commuBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import portfoilo.commuBoard.entity.CommonEntity;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    /*
    생성일시, 생성자, 수정일시, 수정자 등 가장 기본적으로 포함되는 데이터 dto 를 컴포지션 구성으로 사용합니다.
    record 는 불변으로 데이터 변경이 어렵고 확장성이 떨어지므로 전역 응답 설정 등 특수한 경우가 아니라면 dto 는 일반 class 로 정의합니다.
     */

    private String createId;
    private String updateId;
    private LocalDateTime createDtm;
    private LocalDateTime updateDtm;


    /**
     * 생성자, 생성일시, 수정자, 수정일시 반환
     * @param entity CommonEntity 를 상속한 현 엔티티(this)
     * @return BaseDto
     */
    public static BaseDto from(CommonEntity entity) {
        return BaseDto.builder()
                .createId(entity.getCreateId())
                .updateId(entity.getUpdateId())
                .createDtm(entity.getCreateDtm())
                .updateDtm(entity.getUpdateDtm())
                .build();
    }


}
