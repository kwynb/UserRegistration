package com.bragado.userregistration.dto;


import com.bragado.userregistration.components.AttributeEncryptor;
import com.bragado.userregistration.components.UserId;
import com.bragado.userregistration.entities.Login;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

public class LoginDTO {

    private String username;
    private String password;
    private Boolean isLoggedIn;
    private Date createdAt;
    private Date lastLogin;

    public LoginDTO() {}

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
//        this.isLoggedIn = false;
    }

    public LoginDTO(String username, String password, Boolean isLoggedIn) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
    }

    public LoginDTO(String username, String password, Boolean isLoggedIn, Date createdAt, Date lastLogin) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "Login{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", createdAt=" + createdAt +
                ", lastLogin=" + lastLogin +
                '}';
    }

    public Login toLogin() {
        return new Login(username,password);
    }
}
