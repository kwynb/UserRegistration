package com.bragado.userregistration.security;

import com.bragado.userregistration.entities.AuthLogin;
import com.bragado.userregistration.services.UserServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    private JWTUtility jwtUtility;
    private UserServiceImpl userService;

    public AuthTokenFilter() {}

    public AuthTokenFilter(JWTUtility jwtUtility, UserServiceImpl userService) {
        this.jwtUtility = jwtUtility;
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String JWT = parseJWT(request);
            if (JWT != null && jwtUtility.validateJWTToken(JWT)) {
                String username = jwtUtility.getUsernameFromJWTToken(JWT);
                AuthLogin userLog = userService.getByUsername(username).toAuthLogin();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userLog, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }


    private String parseJWT(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7); }
        return null;
    }
}
