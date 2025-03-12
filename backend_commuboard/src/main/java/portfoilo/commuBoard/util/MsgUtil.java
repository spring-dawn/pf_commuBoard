package portfoilo.commuBoard.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component // MessageSource 는 스프링이 관리하는 빈
@RequiredArgsConstructor
public class MsgUtil {
    /*
    message.properties 를 간편히 사용하기 위한 유틸 모음
     */
    private final MessageSource messageSource;

    /**
     * 메시지 가져오기. 동적 파라미터 없는 문구.
     * @param msgCd 사용할 메시지 코드 e.g.) "common.error"
     * @return 메시지 내용 e.g.) 오류가 있습니다. 관리자에 문의하세요.
     */
    public String getMsg(String msgCd) {
        return messageSource.getMessage(msgCd, null, Locale.getDefault());
    }


    /**
     * 메시지 가져오기. 동적 파라미터 있는 문구.
     * @param msgCd 사용할 메시지 코드 e.g.) entity.not_found
     * @param args 메시지에 동적으로 들어갈 파라미터. {0}, {1}... 위치에 파라미터 입력 순서대로 적용
     * @return entity.not_found, "사용자" 입력 시 e.g.) 해당 사용자를 찾을 수 없습니다.
     */
    public String getMsg(String msgCd, Object... args) {
        return messageSource.getMessage(msgCd, args, Locale.getDefault());
    }
}
