package com.deviceomi.security;

import com.deviceomi.service.impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${ominext.app.jwt}")
    private String jwt;

    @Value("${ominext.app.jwtExpiration}")
    private int jwtExpiration;

    public String createJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject(userPrincipal.getUsername())
                                .setIssuedAt(new Date())
                                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                                .signWith(SignatureAlgorithm.HS512, jwt)
                                .compact();
    }

    public String getUserFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwt).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validationJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwt).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
