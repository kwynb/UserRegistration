package com.bragado.userregistration.controllers;

import com.bragado.userregistration.components.Response;
import com.bragado.userregistration.dto.LoginDTO;
import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.components.UserId;
import com.bragado.userregistration.entities.Login;
import com.bragado.userregistration.entities.User;
import com.bragado.userregistration.entities.UserEvent;
import com.bragado.userregistration.messaging.UserProducer;
import com.bragado.userregistration.services.LoginServiceImpl;
import com.bragado.userregistration.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@Validated
@RequestMapping("/users")
@CrossOrigin
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl   userService;
    private final UserProducer      userProducer;
    private final LoginServiceImpl  loginService;


    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createNewUser(@Valid @RequestBody UserDTO userDTO) {
        UserEvent user = new UserEvent("new",userDTO);
        userProducer.sendUser(user);
        return new ResponseEntity<>(userService.createNewUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO,
                                             @Valid @RequestParam(value="id") Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return new ResponseEntity<>(new Response("User Not Found."), HttpStatus.NOT_FOUND);
        }
        User updated = userService.updateUser(userDTO,id);
        UserEvent userEvent = new UserEvent("update", updated.toUserDTO());
        userProducer.sendUser(userEvent);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteUser(@Valid @RequestParam(value="id") Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return new Response("User Not Found.");
        }
        userService.deleteUser(id);
        UserEvent userEvent = new UserEvent("delete", user.toUserDTO());
        userProducer.sendUser(userEvent);
        return new Response("User deleted.");
    }

    @GetMapping(value = "/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getUser(@PathVariable(value="id") @UserId Long id)  {
    User user = userService.getUser(id);
        if (user == null) {
            return new ResponseEntity<>(new Response("User Not Found."), HttpStatus.NOT_FOUND);
        }

        UserEvent userEvent = new UserEvent("get", user.toUserDTO());
        userProducer.sendUser(userEvent);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping(value = {"","/get"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @PostMapping(value = "/login")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
//        Login login = loginService.login(loginDTO);
//        if (login == null) {
//            return new ResponseEntity<>(new Response("Invalid user details."), HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(login, HttpStatus.OK);
//    }

    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> logoutUser(@Valid @RequestBody LoginDTO loginDTO) {
        Login logout = loginService.logout(loginDTO);
        if (logout == null) {
            return new ResponseEntity<>(new Response("Invalid user details."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(logout, HttpStatus.OK);
    }

    @GetMapping(value = "/get/name")
    public ResponseEntity<Object> getUserByName (@RequestParam(value = "fn") String fn,
                                                 @RequestParam(value = "ln") String ln) {
        User user = userService.getByName(fn,ln);
        if (user == null) {
            return new ResponseEntity<>(new Response("User Not Found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/get/firstname")
    public ResponseEntity<Object> getUserByFirstName (@RequestParam(value = "fn") String fn) {
        List<User> user = userService.getByFirstName(fn);
        if (user == null) {
            return new ResponseEntity<>(new Response("User Not Found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/get/lastname")
    public ResponseEntity<Object> getUserByLastName (@RequestParam(value = "ln") String ln) {
        List<User> user = userService.getByLastName(ln);
        if (user == null) {
            return new ResponseEntity<>(new Response("User Not Found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/get/email")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getUserByEmail (@RequestParam(value = "email") String email) {
        User user = userService.getByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(new Response("User Not Found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/get/username")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getUserByUsername (@RequestParam(value = "username") String username) {
        User user = userService.getByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(new Response("User Not Found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
