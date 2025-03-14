package portfoilo.commuBoard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfoilo.commuBoard.entity.system.User;
import portfoilo.commuBoard.repo.UserRepo;
import portfoilo.commuBoard.util.EntityNm;
import portfoilo.commuBoard.util.MsgUtil;

@Slf4j
@Service
@RequiredArgsConstructor // repo import
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final MsgUtil msgUtil;
    private final UserRepo repo;

    /**
     * 스프링 시큐리티 연계 사용자 인증
     *
     * @param userId (default: username) identifying the user whose data is required.
     * @return 사용자 아이디, 권한 목록 등
     * @throws UsernameNotFoundException 사용자 조회 불가
     */
    @Override
    public User loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 계정 정보 + 권한
        User user = repo.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(
//                        InfoMsg.ENTITY_NOT_FOUND.format(EntityNm.USER)
                                msgUtil.getMsg("entity.not_found", EntityNm.USER)
                        )
                );
        user.getAuthorities();

        log.info("asdf 로그인 정보 조회: "+user.getUserId());
        return user;
    }

}
