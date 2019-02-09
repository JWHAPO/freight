package app.hapo.car.freight.common.security.model.token;

import app.hapo.car.freight.common.security.exceptions.JwtExpiredTokenException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * freight
 * Class: RawAccessJwtToken
 * Created by hapo on 2019-02-01.
 * Description: RawAccessJwtToken
 */
public class RawAccessJwtToken implements JwtToken{
    public static final Logger logger = LoggerFactory.getLogger(RawAccessJwtToken.class);

    private String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(String signingKey){
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        }catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex){
            logger.error("Invalid JWT Token",ex);
            throw new BadCredentialsException("Invalid JWT Token: ",ex);
        }catch (ExpiredJwtException expiredEx){
            logger.error("JWT Token is expired", expiredEx);
            throw new JwtExpiredTokenException(this, "JWT Token expired",expiredEx);
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}