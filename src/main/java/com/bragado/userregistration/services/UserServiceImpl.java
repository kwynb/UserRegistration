package com.bragado.userregistration.services;

import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.User;
import com.bragado.userregistration.repositories.UserRepository;
import com.bragado.userregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createNewUser(UserDTO userDTO) {
        return userRepository.save(userDTO.toUser());
    }

    @Override
    public User updateUser(UserDTO userDTO, Long id) {
        if (!userRepository.findById(id).isPresent()) {
            return null;
        }
        User updatedUser = userRepository.findById(id).get();
        updatedUser.setFirstName(userDTO.getFirstName());
        updatedUser.setLastName(userDTO.getLastName());
        updatedUser.setBirthDay(userDTO.getBirthDay());
        updatedUser.setEmail(userDTO.getEmail());
        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            return null;
        }
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
