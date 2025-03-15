package portfoilo.commuBoard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfoilo.commuBoard.entity.system.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    boolean existsByRoleNm(String roleNm);

    Optional<Role> findByRoleNm(String roleNm);
}
