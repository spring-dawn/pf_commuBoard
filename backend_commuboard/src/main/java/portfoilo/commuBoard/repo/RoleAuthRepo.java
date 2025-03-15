package portfoilo.commuBoard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfoilo.commuBoard.entity.system.RoleAuth;

@Repository
public interface RoleAuthRepo extends JpaRepository<RoleAuth, Long> {
}
