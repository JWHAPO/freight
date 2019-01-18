package app.hapo.car.freight.domain.auth.email;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * freight
 * Class: EmailAuthRepository
 * Created by hapo on 2019-01-18.
 * Description:
 */
public interface EmailAuthRepository extends JpaRepository <EmailAuth,Long> {
    Optional<EmailAuth> findByEmail(String email);
    Optional<EmailAuth> findByUserId(Long id);
}
