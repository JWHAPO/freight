package app.hapo.car.freight.api.auth.email;/*
 * Created by hapo
 * Date : 19. 1. 20 오후 10:45
 * Description :
 */

import app.hapo.car.freight.domain.auth.email.EmailAuth;
import app.hapo.car.freight.service.auth.email.EmailAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class EmailAuthController {

    @Autowired
    EmailAuthService emailAuthService;

    @GetMapping(value = "/auth/email/{email}/{authKey}")
    public String confirmEmailAuth(@PathVariable String email, @PathVariable String authKey) {
        Optional<EmailAuth> emailAuth = emailAuthService.findByEmailAndAuthKey(email,authKey);
        LocalDateTime expiredDate = emailAuth.get().getExpiredDate();
        LocalDateTime currentDateTime = LocalDateTime.now();

        if(!emailAuth.isPresent()){
            throw new RuntimeException("Don't have auth history.");
        }
        if(!currentDateTime.isBefore(expiredDate)){
            throw new RuntimeException("After Expired Date. Re sign-up Please.");
        }
        emailAuth.get().setIsAuth("Y");
        emailAuthService.save(emailAuth.get());

        return "authConfirm";
    }
}
