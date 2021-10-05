package com.bragado.userregistration.security.jwt;

import com.bragado.userregistration.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtility {
    private static final Logger logger = LoggerFactory.getLogger(JWTUtility.class);

    private static final String AUTH_SECRET = "secret";
    private static final int    EXP_MS      =  60000;
    private static Date CREATION_TIME = new Date();
    private static Date EXPIRY_TIME = new Date((new Date()).getTime() + EXP_MS);

    public static void setCreationTime(Date creationTime) {
        CREATION_TIME = creationTime;
    }

    public static void setExpiryTime(Date expiryTime) {
        EXPIRY_TIME = expiryTime;
    }


    public String generateJWTToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        if (new Date().getTime() - EXPIRY_TIME.getTime() > 0) {
            setCreationTime(new Date());
            setExpiryTime(new Date((new Date()).getTime() + EXP_MS));
        }
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(CREATION_TIME)
                .setExpiration(EXPIRY_TIME)
                .signWith(SignatureAlgorithm.HS512, AUTH_SECRET)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(AUTH_SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJWTToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(AUTH_SECRET).parseClaimsJws(authToken);
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