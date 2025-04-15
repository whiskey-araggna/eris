package space.araggna.eris.base.domain.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import space.araggna.eris.base.domain.entity.ErisUser;
import space.araggna.eris.base.domain.repository.ErisUserRepository;

@Service
@NoArgsConstructor
public class ErisUserService {

    private ErisUserRepository erisUserRepository;

    public ErisUser getUserByEmail(String userEmail) {
        return erisUserRepository.findByUserEmail(userEmail);
    }
}
