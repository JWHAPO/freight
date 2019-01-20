package app.hapo.car.freight.api.auth.email;/*
 * Created by hapo
 * Date : 19. 1. 20 오후 10:45
 * Description :
 */

import app.hapo.car.freight.domain.auth.email.EmailAuth;
import app.hapo.car.freight.service.auth.email.EmailAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth/email")
public class EmailAuthController {

    @Autowired
    EmailAuthService emailAuthService;

    @PutMapping(value = "/{email}/{authKey}")
    public Optional<EmailAuth> updateEmailAuth(@PathVariable String email, @PathVariable String authKey){
        return emailAuthService.findByEmailAndAuthKey(email,authKey).map(emailAuth -> {
            emailAuth.setIsAuth("Y");
            return emailAuthService.save(emailAuth);
        }).orElseGet(() -> {
           return null;
        });

    }
}
