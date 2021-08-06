package com.bragado.userregistration.services;

import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    User createNewUser(UserDTO userDTO);
    User updateUser(UserDTO userDTO, Long id) throws SQLException;
    void deleteUser(Long id) throws SQLException;
    User getUser(Long id) throws SQLException;
    List<User> getUsers();
}
