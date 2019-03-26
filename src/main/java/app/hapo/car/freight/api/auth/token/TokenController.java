package app.hapo.car.freight.api.auth.token;

import app.hapo.car.freight.WebSecurityConfig;
import app.hapo.car.freight.common.security.JwtSetting;
import app.hapo.car.freight.common.security.exceptions.InvalidJwtToken;
import app.hapo.car.freight.common.security.jwt.extractor.TokenExtractor;
import app.hapo.car.freight.common.security.jwt.verifier.TokenVerifier;
import app.hapo.car.freight.common.security.model.UserContext;
import app.hapo.car.freight.common.security.model.token.JwtToken;
import app.hapo.car.freight.common.security.model.token.JwtTokenFactory;
import app.hapo.car.freight.common.security.model.token.RawAccessJwtToken;
import app.hapo.car.freight.common.security.model.token.RefreshToken;
import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.service.user.UserService;
import jdk.nashorn.internal.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * freight
 * Class: TokenController
 * Created by hapo on 2019-02-01.
 * Description:TokenController
 */
@RestController
public class TokenController {
    public static final Logger logger = LoggerFactory.getLogger(TokenController.class);

    @Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSetting jwtSetting;
    @Autowired private UserService userService;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;

    @RequestMapping(value="/auth/token", method= RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME));

        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);

        RefreshToken refreshToken = RefreshToken.create(rawToken,jwtSetting.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if(!tokenVerifier.verify(jti)){
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        User user = userService.findByEmailHavingAuth(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

        if(user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getEmail(), authorities);

        return tokenFactory.createAccessJwtToken(userContext);

    }

}
