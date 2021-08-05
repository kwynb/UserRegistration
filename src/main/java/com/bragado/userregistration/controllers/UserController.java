package com.bragado.userregistration.controllers;

import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.User;
import com.bragado.userregistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createNewUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createNewUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO, @RequestParam(value="id") Long id) throws SQLException {
        return new ResponseEntity<>(userService.updateUser(userDTO,id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@RequestParam(value="id") Long id) throws SQLException {
        userService.deleteUser(id);
        return "User Deleted.";
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Long id) throws SQLException {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }



}
