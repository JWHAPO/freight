package app.hapo.car.freight.service.auth.email;

import app.hapo.car.freight.domain.auth.email.EmailAuth;

import java.util.Optional;

/**
 * freight
 * Class: EmailAuthService
 * Created by hapo on 2019-01-18.
 * Description:
 */
public interface EmailAuthService {
    Optional<EmailAuth> findByEmail(String email);
    Optional<EmailAuth> findByUserId(Long id);
    Optional<EmailAuth> save(EmailAuth emailAuth);
}
