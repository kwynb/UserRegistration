package com.bragado.userregistration.security;


import com.bragado.userregistration.entities.AuthLogin;
import com.bragado.userregistration.entities.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtility {

    private static final Logger log = LoggerFactory.getLogger(JWTUtility.class);
    private static final String AUTH_SECRET = "projectPassword-0314";
    private static final Integer EXP_MS = 300000;
    private static final Date DATE_NOW = new Date();
    private static final Date EXP_DATE = new Date(new Date().getTime() + EXP_MS);

    public String generateJWTToken(Authentication auth) {

        AuthLogin user = (AuthLogin) auth.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(DATE_NOW)
                .setExpiration(EXP_DATE)
                .signWith(SignatureAlgorithm.HS256, AUTH_SECRET.getBytes())
                .compact();

    }

    public String getUsernameFromJWTToken(String token) {
        return Jwts.parser().setSigningKey(AUTH_SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJWTToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(AUTH_SECRET.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
