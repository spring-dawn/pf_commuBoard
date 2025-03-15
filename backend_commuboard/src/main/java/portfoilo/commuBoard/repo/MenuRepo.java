package portfoilo.commuBoard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfoilo.commuBoard.entity.system.Menu;

import java.util.Optional;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {
    Optional<Menu> findByUrl(String url);

    boolean existsMenuByUrl(String url);
}
