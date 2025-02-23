package portfoilo.commuBoard.dto;

public record ResponseDto(String code, String msg, String time) {
    // Java 17 Record. 내용이 짧고 setter 사용 여지가 없는 dto 의 경우 record 작성 시도.
}