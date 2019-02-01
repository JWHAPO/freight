package app.hapo.car.freight.common.security.exceptions;

import app.hapo.car.freight.common.security.model.token.JwtToken;
import org.springframework.security.core.AuthenticationException;

/**
 * freight
 * Class: JwtExpiredTokenException
 * Created by hapo on 2019-02-01.
 * Description: JwtExpiredTokenException
 */
public class JwtExpiredTokenException extends AuthenticationException {
    private static final long serialVersionUID = -5959543783324224864L;
    
    private JwtToken token;

    public JwtExpiredTokenException(String msg) {
        super(msg);
    }

    public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
