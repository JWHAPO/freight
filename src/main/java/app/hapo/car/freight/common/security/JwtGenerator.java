package app.hapo.car.freight.common.security;/*
 * Created by hapo
 * Date : 19. 1. 30 오전 12:36
 * Description :JwtGenerator
 */

import app.hapo.car.freight.common.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

    public String generate(JwtUser jwtUser){

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role",jwtUser.getRole());

        return Jwts.builder()
        .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact();

    }
}
