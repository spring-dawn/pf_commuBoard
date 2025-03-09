package portfoilo.commuBoard.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfoilo.commuBoard.entity.system.User;
import portfoilo.commuBoard.repo.UserRepo;
import portfoilo.commuBoard.util.EntityNm;
import portfoilo.commuBoard.util.enums.InfoMsg;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepo repo;


    @Transactional
    public void countLoginFailure(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(InfoMsg.ENTITY_NOT_FOUND.format(EntityNm.USER)));

        user.updateLoginFailCnt();
    }

    @Override
    public void initLoginFailure(Long id) {
//        1) find target
        User user = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(InfoMsg.ENTITY_NOT_FOUND.format(EntityNm.USER)));

        user.initLoginFailCnt();
    }

}
