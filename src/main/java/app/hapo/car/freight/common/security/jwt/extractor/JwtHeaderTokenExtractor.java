package app.hapo.car.freight.common.security.jwt.extractor;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * freight
 * Class: JwtHeaderTokenExtractor
 * Created by hapo on 2019-02-01.
 * Description:JwtHeaderTokenExtractor
 */
@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {

    public static String HEADER_PREFIX = "Bearer ";

    @Override
    public String extract(String header) {
        if(StringUtils.isEmpty(header)){
            throw new AuthenticationServiceException("Authorization header can not be blank.");
        }
        if(header.length() < HEADER_PREFIX.length()){
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }

        return header.substring(HEADER_PREFIX.length());
    }
}
