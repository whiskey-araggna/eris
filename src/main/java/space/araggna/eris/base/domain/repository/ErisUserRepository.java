package space.araggna.eris.base.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.araggna.eris.base.domain.entity.ErisUser;

import java.util.UUID;

@Repository
public interface ErisUserRepository extends JpaRepository<ErisUser, UUID> {
    ErisUser findByUserEmail(String userEmail);
}
