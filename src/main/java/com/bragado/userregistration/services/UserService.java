package com.bragado.userregistration.services;

import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.User;
import com.bragado.userregistration.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    User createNewUser(UserDTO userDTO);
    User updateUser(UserDTO userDTO, Long id);
    void deleteUser(Long id);
    User getUser(Long id);
    List<User> getUsers();
    User getByEmail(String email);
}
