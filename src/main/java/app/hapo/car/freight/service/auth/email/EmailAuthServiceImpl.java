package app.hapo.car.freight.service.auth.email;

import app.hapo.car.freight.domain.auth.email.EmailAuth;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * freight
 * Class: EmailAuthServiceImpl
 * Created by hapo on 2019-01-18.
 * Description:
 */

@Service
@Transactional
public class EmailAuthServiceImpl implements EmailAuthService {
    @Override
    public Optional<EmailAuth> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<EmailAuth> findByUserId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<EmailAuth> save(EmailAuth emailAuth) {
        return Optional.empty();
    }
}
