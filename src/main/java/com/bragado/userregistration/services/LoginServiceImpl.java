package com.bragado.userregistration.services;

import com.bragado.userregistration.dto.LoginDTO;
import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.Login;
import com.bragado.userregistration.repositories.LoginRepository;
import com.bragado.userregistration.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    private LoginRepository loginRepository;
    private UserServiceImpl userService;

    public LoginServiceImpl(LoginRepository loginRepository, UserServiceImpl userService) {
        this.loginRepository = loginRepository;
        this.userService = userService;
    }

    @Override
    public Login login(LoginDTO loginDTO) {
        boolean isValid = userService.verifyLogin(loginDTO.getUsername(), loginDTO.getPassword());
        if (isValid == false) {
            return null;
        }
        Login login = loginRepository.findByUsername(loginDTO.getUsername());
        login.setUsername(loginDTO.getUsername());
        login.setPassword(loginDTO.getPassword());
        login.setLoggedIn(true);
        return loginRepository.save(login);
    }

    @Override
    public Login logout(LoginDTO loginDTO) {
        boolean isValid = userService.verifyLogin(loginDTO.getUsername(), loginDTO.getPassword());
        Login loggedIn = loginRepository.findByUsername(loginDTO.getUsername());
        if (loggedIn.getLoggedIn() == false) {
            return loggedIn;
        }
        loggedIn.setLoggedIn(false);
        return loginRepository.save(loggedIn);

    }


}
