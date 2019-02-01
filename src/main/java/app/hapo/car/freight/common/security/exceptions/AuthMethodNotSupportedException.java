package app.hapo.car.freight.common.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * freight
 * Class: AuthMethodNotSupportedException
 * Created by hapo on 2019-02-01.
 * Description: AuthMethodNotSupportedException
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {
    private static final long serialVersionUID = 3705043083010304496L;

    public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}
