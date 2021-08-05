package com.bragado.userregistration.services.implementation;

import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.User;
import com.bragado.userregistration.repositories.UserRepository;
import com.bragado.userregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User newUser(UserDTO userDTO) {
        return new User(userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getBirthDay(),
                userDTO.getEmail());
    }

    public User userById(UserDTO userDTO, Long id) {
        User currentUser = userRepository.findById(id).get();
        currentUser.setFirstName(userDTO.getFirstName());
        currentUser.setLastName(userDTO.getLastName());
        currentUser.setBirthDay(userDTO.getBirthDay());
        currentUser.setEmail(userDTO.getEmail());
        return currentUser;
    }


    @Override
    public User createNewUser(UserDTO userDTO) {
        return userRepository.save(newUser(userDTO));
    }

    @Override
    public User updateUser(UserDTO userDTO, Long id) throws SQLException {
        if (!userRepository.findById(id).isPresent()) {
            throw new SQLException("User does not exist.");
        }
        return userRepository.save(userById(userDTO,id));
    }

    @Override
    public void deleteUser(Long id) throws SQLException{
        if (!userRepository.findById(id).isPresent()) {
            throw new SQLException("User does not exist.");
        }
        userRepository.delete(userRepository.findById(id).get());
    }

    @Override
    public User getUser(Long id) throws SQLException{
        if (!userRepository.findById(id).isPresent()) {
            throw new SQLException("User does not exist.");
        }
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
