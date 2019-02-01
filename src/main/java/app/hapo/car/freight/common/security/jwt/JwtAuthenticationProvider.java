package app.hapo.car.freight.common.security.jwt;

import app.hapo.car.freight.common.security.JwtSetting;
import app.hapo.car.freight.common.security.model.UserContext;
import app.hapo.car.freight.common.security.model.token.RawAccessJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * freight
 * Class: JwtAuthenticationProvider
 * Created by hapo on 2019-02-01.
 * Description:JwtAuthenticationProvider
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtSetting jwtSetting;

    @Autowired
    public JwtAuthenticationProvider(JwtSetting jwtSetting) {
        this.jwtSetting = jwtSetting;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        RawAccessJwtToken rawAccessJwtToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessJwtToken.parseClaims(jwtSetting.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        List<GrantedAuthority> authorities = scopes.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        UserContext context = UserContext.create(subject, authorities);

        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
