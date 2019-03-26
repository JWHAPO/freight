package app.hapo.car.freight.common.security.ajax;/*
 * Created by hapo
 * Date : 19. 2. 3 오후 1:48
 * Description : AjaxAuthenticationProvider
 */

import app.hapo.car.freight.common.security.model.UserContext;
import app.hapo.car.freight.domain.user.User;
import app.hapo.car.freight.service.user.UserService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder encoder;
    private final UserService userService;

    @Autowired
    public AjaxAuthenticationProvider(BCryptPasswordEncoder encoder, UserService userService) {
        this.encoder = encoder;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Assert.notNull(authentication, "No authentication data provided");

        String email = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();

        User user = userService.findByEmailHavingAuth(email).orElseThrow(() -> new UsernameNotFoundException("User not found : "+email));

        if(!password.equals(user.getPassword())){
            throw new BadCredentialsException("Authentication Failed. Email or Password not valid.");
        }

        //BCrypt
//        if(!encoder.matches(password, user.getPassword())){
//            throw new BadCredentialsException("Authentication Failed. Email or Password not valid.");
//        }

        if(user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getEmail(), authorities);
        return new UsernamePasswordAuthenticationToken(userContext,null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
