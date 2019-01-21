package app.hapo.car.freight.api.auth.email;/*
 * Created by hapo
 * Date : 19. 1. 20 오후 10:45
 * Description :
 */

import app.hapo.car.freight.domain.auth.email.EmailAuth;
import app.hapo.car.freight.service.auth.email.EmailAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "/auth/email")
public class EmailAuthController {

    @Autowired
    EmailAuthService emailAuthService;

    @GetMapping(value = "/{email}/{authKey}")
    public Optional<EmailAuth> updateEmailAuth(@PathVariable String email, @PathVariable String authKey){
        return emailAuthService.findByEmailAndAuthKey(email,authKey).map(emailAuth -> {

            LocalDateTime expiredDate = emailAuth.getExpiredDate();
            LocalDateTime currentDateTime = LocalDateTime.now();

            if(!currentDateTime.isBefore(expiredDate)){
                throw new RuntimeException("After Expired Date. Re sign-up Please.");
            }

            emailAuth.setIsAuth("Y");
            return emailAuthService.save(emailAuth);
        }).orElseGet(() -> {
           throw new RuntimeException("Wrong Auth Key");
        });

    }
}
