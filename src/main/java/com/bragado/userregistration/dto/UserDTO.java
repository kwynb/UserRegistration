package com.bragado.userregistration.dto;

import com.bragado.userregistration.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

public class UserDTO {

    private String firstName;
    private String lastName;
    @Past(message = "Birthday should be valid.")
    @JsonFormat(pattern="MM/dd/yyyy", shape = JsonFormat.Shape.STRING, timezone = "Asia/Manila")
    private Date birthDay;
    @Email(message = "Email should be valid.")
    private String email;

    public UserDTO(String firstName, String lastName, Date birthDay, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.email = email;
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

    public User toUser() {
        return new User(firstName,lastName,birthDay,email);
    }
}
