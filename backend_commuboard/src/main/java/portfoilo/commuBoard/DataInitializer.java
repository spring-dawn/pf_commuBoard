package portfoilo.commuBoard;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import portfoilo.commuBoard.dto.system.AuthDto;
import portfoilo.commuBoard.dto.system.MenuDto;
import portfoilo.commuBoard.dto.system.RoleDto;
import portfoilo.commuBoard.dto.system.UserDto;
import portfoilo.commuBoard.entity.system.*;
import portfoilo.commuBoard.repo.*;
import portfoilo.commuBoard.util.SHA256Util;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final MenuRepo menuRepo;
    private final RoleRepo roleRepo;
    private final AuthRepo authRepo;
    private final RoleAuthRepo roleAuthRepo;
    private final UserRepo userRepo;
    private final CommonCodeRepo codeRepo;

    // 권한 종류. 등록(생성), 조회(디폴트), 수정, 삭제
    private static final Character[] ACCESS_TYPE = {'C', 'R', 'U', 'D'};

    /*
   임시 데이터 샘플
    */
    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 1. 메뉴, 메뉴별 auth 생성
        try {
            MenuDto menu1 = createMenuIfNotExist("/test1", "메뉴1", 0, null);
            MenuDto menu2 = createMenuIfNotExist("/test2", "메뉴2", 1, null);
            MenuDto menu_3 = createMenuIfNotExist("/test3", "메뉴3", 2, null);

            MenuDto menu2_1 = createMenuIfNotExist(menu2.getUrl() + "/sub1", "메뉴2_1", 0, menu2.getUrl());
            MenuDto menu_2 = createMenuIfNotExist(menu2.getUrl() + "/sub2", "메뉴2_1", 1, menu2.getUrl());

        } catch (Exception e) {
            log.error("Error while initializing menu entities", e);
        }

        // 2. Role, RoleAuth 생성
        List<AuthDto> authDtoList = authRepo.findAll().stream().map(Auth::toRes).collect(Collectors.toList());
        List<Long> adminAuthList = authDtoList.stream().map(AuthDto::id).collect(Collectors.toList());
        List<Long> userAuthList = authDtoList.stream().filter(dto -> !dto.url().contains("test1"))
                .map(AuthDto::id)
                .collect(Collectors.toList());

        RoleDto role1 = createRoleIfNotExist("관리자", 0, "모든 메뉴, 기능 사용 가능", adminAuthList);
        RoleDto role2 = createRoleIfNotExist("유저", 1, "커뮤니티 이용 고객", userAuthList);

        // 3. 테스트용 사용자 계정 생성
        try {
            createUserIfNotExist("test1", "test1", 'Y', role1.getId());
            createUserIfNotExist("test2", null, 'N', role2.getId());
        } catch (Exception e) {
            log.error("Error while initializing user entities", e);
        }

        // TODO: 공통 코드

    }


    @Transactional
    protected MenuDto createMenuIfNotExist(String url, String menuNm, int order, String parentUrl) {
        Menu prev = menuRepo.findByUrl(url).orElse(null);
        if (prev != null) {
            return prev.toRes();
        }

        // useYn 은 디폴트, depth 는 안 써도 될 것 같다...
        Menu menu = menuRepo.save(
                Menu.builder()
                        .url(url)
                        .menuNm(menuNm)
                        .order(order)
                        .parent(menuRepo.findByUrl(parentUrl).orElse(null))
                        .build()
        );

        // 메뉴의 액세스 권한도 같이 만들기
        for (Character type : ACCESS_TYPE) {
            authRepo.save(Auth.builder()
                    .menu(menu)
                    .type(type)
                    .build());
        }

        // res
        return menu.toRes();
    }

    @Transactional
    protected RoleDto createRoleIfNotExist(String roleNm, int order, String memo, List<Long> authIdList) {
        Role prev = roleRepo.findByRoleNm(roleNm).orElse(null);
        if (prev != null) {
            return prev.toRes();
        }

        // role 생성
        Role role = roleRepo.save(
                Role.builder()
                        .roleNm(roleNm)
                        .order(order)
                        .memo(memo)
                        .useYn('Y')
                        .build()
        );

        // roleAuth 생성
        List<RoleAuth> roleAuthList = new ArrayList<>();
        for (Long access : authIdList) {
            RoleAuth roleAuth = roleAuthRepo.save(
                    RoleAuth.builder()
                            .role(role)
                            .auth(authRepo.findById(access).orElseThrow(() -> new EntityNotFoundException("Role auth not found")))
                            .build()
            );
            roleAuthList.add(roleAuth);
        }

        //
        role.addAuthList(roleAuthList);
        return role.toRes();
    }

    @Transactional
    protected UserDto createUserIfNotExist(String userId, String nick, Character admYn, Long roleId) throws NoSuchAlgorithmException {
        if (userRepo.existsByUserId(userId)) return null;

        // 비밀번호는 일괄
        SHA256Util.PwDto pwDto = SHA256Util.createPw(userId);

        return userRepo.save(
                User.builder()
                        .userId(userId)
                        .userPw(pwDto.salted())
                        .salt(pwDto.salt())
                        .admYn(admYn)
                        .userNick(nick)
                        .email(userId + "@commuBoardTest.com")
                        .role(roleRepo.findById(roleId)
                                .orElseThrow(() -> new EntityNotFoundException("Role Not Found"))
                        )
                        .build()
        ).toRes();

    }


}
