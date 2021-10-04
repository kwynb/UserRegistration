package com.bragado.userregistration.controllers;


import com.bragado.userregistration.dto.LoginDTO;
import com.bragado.userregistration.repositories.AuthRepository;
import com.bragado.userregistration.repositories.LoginRepository;
import com.bragado.userregistration.repositories.UserRepository;
import com.bragado.userregistration.security.response.JWTResponse;
import com.bragado.userregistration.security.JWTUtility;
import com.bragado.userregistration.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthenticationManager   authManager;
    UserRepository          userRepository;
    LoginRepository         loginRepository;
    AuthRepository          authRepository;
    JWTUtility              jwtUtility;


    public AuthController(AuthenticationManager authManager, UserRepository userRepository, LoginRepository loginRepository, AuthRepository authRepository, JWTUtility jwtUtility) {
        this.authManager     =  authManager;
        this.userRepository  =  userRepository;
        this.loginRepository =  loginRepository;
        this.authRepository  =  authRepository;
        this.jwtUtility      =  jwtUtility;
    }

//    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO.getUsername());
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String JWT = jwtUtility.generateJWTToken(authentication);
        System.out.println(JWT);
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        JWTResponse userInstance = new JWTResponse(
                JWT,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities().stream().collect(Collectors.toList()));
        return ResponseEntity.ok(userInstance);
    }

}
