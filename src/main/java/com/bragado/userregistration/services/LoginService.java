package com.bragado.userregistration.services;

import com.bragado.userregistration.dto.LoginDTO;
import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.Login;
import com.bragado.userregistration.entities.User;

import java.util.List;

public interface LoginService {

    Login login(LoginDTO loginDTO);
    Login logout(LoginDTO loginDTO);
}
