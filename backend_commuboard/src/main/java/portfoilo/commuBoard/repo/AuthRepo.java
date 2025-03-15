package portfoilo.commuBoard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfoilo.commuBoard.entity.system.Auth;

@Repository
public interface AuthRepo extends JpaRepository<Auth, Long> {
    boolean existsByType(Character type);
}
