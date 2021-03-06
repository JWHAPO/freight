package app.hapo.car.freight.common.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * freight
 * Class: UserContext
 * Created by hapo on 2019-02-01.
 * Description: UserContext
 */
public class UserContext {
    private final String email;
    private final List<GrantedAuthority> authorities;

    public UserContext(String email, List<GrantedAuthority> authorities) {
        this.email = email;
        this.authorities = authorities;
    }

    public static UserContext create(String email, List<GrantedAuthority> authorities){
        if(StringUtils.isEmpty(email)) throw new IllegalArgumentException("Email is blank: "+ email);
        return new UserContext(email, authorities);
    }

    public String getEmail() {
        return email;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
