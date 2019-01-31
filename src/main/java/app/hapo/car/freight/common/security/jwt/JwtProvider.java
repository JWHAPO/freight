package app.hapo.car.freight.common.security.jwt;

import app.hapo.car.freight.common.security.services.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * freight
 * Class: JwtProvider
 * Created by hapo on 2019-01-31.
 * Description: JwtProvider
 */

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${hapo.app.bimil}")
    private String bimil;

    @Value("${hapo.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication){

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, bimil)
                .compact();
    }

    public String getEmailFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(bimil)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(bimil).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid JWT signature -> Message: {}", e);
        }catch (MalformedJwtException e){
            logger.error("Invalid JWT token -> Message: {}", e);
        }catch (ExpiredJwtException e){
            logger.error("Expired JWT token -> Message: {}", e);
        }catch (UnsupportedJwtException e){
            logger.error("Unsupported  JWT token -> Message: {}", e);
        }catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }
}
