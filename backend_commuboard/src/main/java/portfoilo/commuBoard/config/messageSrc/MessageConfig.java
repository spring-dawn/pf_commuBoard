package portfoilo.commuBoard.config.messageSrc;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {
    /*
    전역의 안내 메시지 등의 문구를 .properties 로 관리하기 위한 spring boot 설정
    간단하게는 application.yml 에서도 설정 가능한 듯.
    basename 설정과 같은 properties 파일을 /resources 아래에 위치
    _ko, _en 등 다국어 식별명을 붙여도 파일을 식별하는 데 문제 없는 모양
     */

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages, errors"); // 쉼표로 파일명 추가 가능
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
