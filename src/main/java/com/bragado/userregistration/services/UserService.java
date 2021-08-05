package com.bragado.userregistration.services;

import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    public User createNewUser(UserDTO userDTO);
    public User updateUser(UserDTO userDTO, Long id) throws SQLException;
    public void deleteUser(Long id) throws SQLException;
    public User getUser(Long id) throws SQLException;
    public List<User> getUsers();
}
