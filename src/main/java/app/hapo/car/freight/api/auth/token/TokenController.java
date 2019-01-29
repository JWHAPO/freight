package app.hapo.car.freight.api.auth.token;/*
 * Created by hapo
 * Date : 19. 1. 30 오전 12:33
 * Description : TokenController
 */

import app.hapo.car.freight.common.model.JwtUser;
import app.hapo.car.freight.common.security.JwtGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping(value = "/token")
    public String generate(@RequestBody final JwtUser jwtUser){
        return jwtGenerator.generate(jwtUser);
    }
}
