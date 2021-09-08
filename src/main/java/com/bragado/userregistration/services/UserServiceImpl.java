package com.bragado.userregistration.services;

import com.bragado.userregistration.dto.LoginDTO;
import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.Login;
import com.bragado.userregistration.entities.User;
import com.bragado.userregistration.repositories.LoginRepository;
import com.bragado.userregistration.repositories.UserRepository;
import com.bragado.userregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private LoginRepository loginRepository;

    public UserServiceImpl(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    public User createNewUser(UserDTO userDTO) {
        User user = userDTO.toUser();
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        LoginDTO login = new LoginDTO(username, password);
        loginRepository.save(login.toLogin());
        return userRepository.save(user);
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
        if (updatedUser.getUsername() != userDTO.getUsername()) {
            Login login = loginRepository.findByUsername(updatedUser.getUsername());
            Login myLog = loginRepository.findById(login.getId()).get();
            myLog.setUsername(userDTO.getUsername());
            myLog.setPassword(userDTO.getPassword());
        }
        updatedUser.setUsername(userDTO.getUsername());
        updatedUser.setPassword(userDTO.getPassword());
        return userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        loginRepository.delete(loginRepository.findByUsername(user.getUsername()));
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

    @Override
    public List<User> getByFirstName(String firstname) { return userRepository.findByFirstName(firstname); }

    @Override
    public List<User> getByLastName(String lastname) { return userRepository.findByLastName(lastname); }

    @Override
    public User getByName(String firstname, String lastname) { return userRepository.findByName(firstname,lastname); }

    @Override
    public User getByEmail(String email) { return userRepository.findByEmail(email); }

    @Override
    public User getByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    public boolean verifyLogin(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null ) {
            return false;
        }
        if (!password.equals(user.getPassword())) {
            return false;
        }
        return true;
    }
}
