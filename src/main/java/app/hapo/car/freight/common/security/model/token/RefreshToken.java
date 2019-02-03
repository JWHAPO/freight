package app.hapo.car.freight.common.security.model.token;

import app.hapo.car.freight.common.security.model.Scopes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * freight
 * Class: RefreshToken
 * Created by hapo on 2019-02-01.
 * Description: RefreshToken
 */
@SuppressWarnings("unchecked")
public class RefreshToken implements JwtToken {

    public static final Logger logger = LoggerFactory.getLogger(RefreshToken.class);

    private Jws<Claims> claims;

    private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
        logger.info("signingKey:"+signingKey);

        Jws<Claims> claims = token.parseClaims(signingKey);

        List<String> scopes = claims.getBody().get("scopes", List.class);
        if (scopes == null || scopes.isEmpty() 
                || !scopes.stream().filter(scope -> Scopes.REFRESH_TOKEN.authority().equals(scope)).findFirst().isPresent()) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(claims));
    }

    @Override
    public String getToken() {
        return null;
    }

    public Jws<Claims> getClaims() {
        return claims;
    }
    
    public String getJti() {
        return claims.getBody().getId();
    }
    
    public String getSubject() {
        return claims.getBody().getSubject();
    }
}
