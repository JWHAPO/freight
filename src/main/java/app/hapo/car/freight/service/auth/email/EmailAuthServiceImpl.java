package app.hapo.car.freight.service.auth.email;

import app.hapo.car.freight.domain.auth.email.EmailAuth;
import app.hapo.car.freight.domain.auth.email.EmailAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private EmailAuthRepository emailAuthRepository;

    @Override
    public Optional<EmailAuth> findByEmail(String email) {
        return emailAuthRepository.findByEmail(email);
    }

    @Override
    public Optional<EmailAuth> findByEmailAndIsAuth(String email, String isAuth) {
        return emailAuthRepository.findByEmailAndIsAuth(email,isAuth);
    }

    @Override
    public Optional<EmailAuth> findByUserId(Long id) {
        return emailAuthRepository.findByUserId(id);
    }

    @Override
    public Optional<EmailAuth> findByEmailAndAuthKey(String email, String authKey) {
        return emailAuthRepository.findByEmailAndAuthKey(email,authKey);
    }

    @Override
    public Optional<EmailAuth> save(EmailAuth emailAuth) {
        return Optional.of(emailAuthRepository.save(emailAuth));
    }
}
