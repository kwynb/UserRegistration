package com.bragado.userregistration.dto;

import com.bragado.userregistration.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.validation.constraints.Past;
import java.util.Date;

public class UserDTO {

    private String firstName;
    private String lastName;
    private Date birthDay;
    private String email;
    private String username;
    private String password;
    private Date createdAt;
    private Date lastModified;

    public UserDTO() {}

    public UserDTO(String firstName, String lastName, Date birthDay, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserDTO(String firstName, String lastName, Date birthDay, String email, String username, String password, Date createdAt, Date lastModified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getLastModified() { return lastModified; }

    public void setLastModified(Date lastModified) { this.lastModified = lastModified; }
    public User toUser() {
        return new User(firstName,lastName,birthDay,email, username, password);
    }

}
