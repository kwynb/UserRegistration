package com.bragado.userregistration.controllers;


import com.bragado.userregistration.components.Response;
import com.bragado.userregistration.dto.LoginDTO;
import com.bragado.userregistration.entities.UserTest;
import com.bragado.userregistration.repositories.JWTResponseRepository;
import com.bragado.userregistration.repositories.UserRepository;
import com.bragado.userregistration.repositories.UserTestRepository;
import com.bragado.userregistration.security.jwt.req.LoginRequest;
import com.bragado.userregistration.security.jwt.JWTResponse;
import com.bragado.userregistration.security.jwt.JWTUtility;
import com.bragado.userregistration.security.UserDetailsImpl;
import com.bragado.userregistration.security.jwt.req.RegisterRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    AuthenticationManager   authenticationManager;
    UserRepository          userRepository;
    UserTestRepository      userTestRepository;
    PasswordEncoder         encoder;
    JWTUtility              jwtUtility;
    JWTResponseRepository jwtRepository;


    private static final String AUTH_SECRET = "secret";
    private static final int EXP_MS = 60000;
    private static Date CREATION_TIME = new Date();
    private static Date EXPIRY_TIME = new Date((new Date()).getTime() + EXP_MS);

    public static void setCreationTime(Date creationTime) {
        CREATION_TIME = creationTime;
    }

    public static void setExpiryTime(Date expiryTime) {
        EXPIRY_TIME = expiryTime;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJWTToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JWTResponse response = new JWTResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role.get(0));

        if (new Date().getTime() - EXPIRY_TIME.getTime() > 0) {
            response.setAccessToken(jwt);
            setCreationTime(new Date());
            setExpiryTime(new Date((new Date()).getTime() + EXP_MS));
        }
        response.setTokenCreationTime(CREATION_TIME);
        response.setTokenExpiryTime(EXPIRY_TIME);
        jwtRepository.save(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LoginDTO loginRequest) {
        System.out.println(loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Jwts.builder()
                .setSubject((userDetails.getUsername()))
                .setExpiration(new Date())
                .signWith(SignatureAlgorithm.HS512, AUTH_SECRET)
                .compact();

        List<String> role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JWTResponse response = new JWTResponse(null,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role.get(0));
        response.setTokenExpiryTime(new Date());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        System.out.println(registerRequest.getUsername());
        if (userTestRepository.findByUsername(registerRequest.getUsername()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new Response("Error: Username is already taken!"));
        }

        UserTest newUser = new UserTest(registerRequest.getFirstname(),
                registerRequest.getLastname(),
                registerRequest.getBirthday(),
                registerRequest.getEmail(),
                registerRequest.getUsername(),
                encoder.encode(registerRequest.getPassword()));

        userTestRepository.save(newUser);

        return ResponseEntity.ok(newUser);
    }


}
