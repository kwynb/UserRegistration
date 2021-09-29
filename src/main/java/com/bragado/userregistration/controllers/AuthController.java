package com.bragado.userregistration.controllers;


import com.bragado.userregistration.dto.LoginDTO;
import com.bragado.userregistration.entities.AuthLogin;
import com.bragado.userregistration.entities.Login;
import com.bragado.userregistration.repositories.AuthRepository;
import com.bragado.userregistration.repositories.LoginRepository;
import com.bragado.userregistration.repositories.UserRepository;
import com.bragado.userregistration.security.JWTResponse;
import com.bragado.userregistration.security.JWTUtility;
import com.bragado.userregistration.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthenticationManager authManager;
    UserRepository userRepository;
    LoginRepository loginRepository;
    AuthRepository authRepository;
    PasswordEncoder passEncoder;
    JWTUtility jwtUtility;


    public AuthController(AuthenticationManager authManager, UserRepository userRepository, LoginRepository loginRepository, AuthRepository authRepository, PasswordEncoder passEncoder, JWTUtility jwtUtility) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.authRepository = authRepository;
        this.passEncoder = passEncoder;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String JWT = jwtUtility.generateJWTToken(authentication);

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        JWTResponse userInstance = new JWTResponse(
                JWT,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
        return ResponseEntity.ok(userInstance);
    }

}
