package com.bragado.userregistration.controllers;

import com.bragado.userregistration.dto.Response;
import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.User;
import com.bragado.userregistration.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createNewUser(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createNewUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO, @Valid @RequestParam(value="id") Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return new ResponseEntity<>(new Response("User Not Found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.updateUser(userDTO,id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteUser(@Valid @RequestParam(value="id") Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return new Response("User Not Found.");
        }
        userService.deleteUser(id);
        return new Response("User deleted.");
    }

    @GetMapping(value = "/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getUser(@PathVariable(value="id") @Pattern(regexp = "^[0-9]*$") Long id)  {
        User user = userService.getUser(id);
        if (user == null) {
            return new ResponseEntity<>(new Response("User Not Found."), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = {"","/get"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
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

}
