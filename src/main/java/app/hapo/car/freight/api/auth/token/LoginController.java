package app.hapo.car.freight.api.auth.token;/*
 * Created by hapo
 * Date : 19. 2. 2 오전 12:59
 * Description : LoginController
 */

import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.service.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    UserService userService;
    @RequestMapping(value="/api/auth/login", method= RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public String login(@RequestBody User loginUser) throws ServletException {
        String token = "";
        String refreshToken = "";

        if(loginUser.getEmail() == null || loginUser.getPassword() == null){
            throw new ServletException("Please fill in email and password");
        }

        String email = loginUser.getEmail();
        String password = loginUser.getPassword();

        Optional<User> user = userService.findByEmail(email);

        if(!user.isPresent()) throw new ServletException("User email not found");

        String pwd = user.get().getPassword();

        if(!password.equals(pwd)) throw new ServletException("Invalid login. Please check your email and password.");

        token = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        return token;
    }
}
