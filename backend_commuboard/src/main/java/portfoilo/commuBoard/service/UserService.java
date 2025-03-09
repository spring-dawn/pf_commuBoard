package portfoilo.commuBoard.service;

public interface UserService {
    /**
     * 로그인 실패 시 횟수 +1 씩 증가
     * @param id 사용자 번호
     */
    public void countLoginFailure(Long id);


    /**
     * 로그인 실패 횟수 초기화
     * @param id 사용자 번호
     */
    public void initLoginFailure(Long id);

}
