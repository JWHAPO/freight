package app.hapo.car.freight.api.auth.token;/*
 * Created by hapo
 * Date : 19. 1. 30 오전 12:33
 * Description : TokenController
 */

import app.hapo.car.freight.common.security.jwt.JwtProvider;
import app.hapo.car.freight.domain.user.Role.RoleRepository;
import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping(value = "/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody User user){
        logger.info("Into authenticateUser");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail()
                        ,user.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(jwt);
    }
}
