package portfoilo.commuBoard.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfoilo.commuBoard.entity.system.CommonCode;

@Repository
public interface CommonCodeRepo extends JpaRepository<CommonCode, Long> {
}
