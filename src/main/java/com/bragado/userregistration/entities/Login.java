package com.bragado.userregistration.entities;

import com.bragado.userregistration.components.AttributeEncryptor;
import com.bragado.userregistration.components.UserId;
import com.bragado.userregistration.dto.LoginDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="login")
@EntityListeners(AuditingEntityListener.class)
public class Login {


    @Id
    @UserId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long    id;

    @Column(name = "username")
    private String  username;

    @Column(name = "password")
    @Convert(converter = AttributeEncryptor.class)
    private String  password;

    @Column(name = "is_logged_in")
    private Boolean isLoggedIn;

    @CreatedDate
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Asia/Manila")
    private Date    createdAt;

    @LastModifiedDate
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Asia/Manila")
    private Date    lastLogin;

    public Login() {}

    public Login(String username, String password) {
        this.username   = username;
        this.password   = password;
        this.isLoggedIn = false;
    }

    public Login(String username, String password, Boolean isLoggedIn) {
        this.username   = username;
        this.password   = password;
        this.isLoggedIn = isLoggedIn;
    }

    public Login(Long id, String username, String password, Boolean isLoggedIn, Date createdAt, Date lastLogin) {
        this.id         = id;
        this.username   = username;
        this.password   = password;
        this.isLoggedIn = isLoggedIn;
        this.createdAt  = createdAt;
        this.lastLogin  = lastLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", createdAt=" + createdAt +
                ", lastLogin=" + lastLogin +
                '}';
    }

    public LoginDTO toLoginDTO() {
        return new LoginDTO(username,password);
    }
}
