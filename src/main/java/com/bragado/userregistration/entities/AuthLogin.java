package com.bragado.userregistration.entities;

import com.bragado.userregistration.components.AttributeEncryptor;
import com.bragado.userregistration.components.UserId;
import com.bragado.userregistration.dto.LoginDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Date;


@Entity
@Table(name="auth_login")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthLogin {

    @Id
    @UserId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @Convert(converter = AttributeEncryptor.class)
    private String password;

    @Column(name = "email")
    @Email(message = "Email should be valid.")
    private String email;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "type")
    private String type = "Bearer";

    @Column(name = "is_logged_in")
    private Boolean isLoggedIn;

    @CreatedDate
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Asia/Manila")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "expired_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Asia/Manila")
    private Date expiredAt;

    public AuthLogin() {}

    public AuthLogin(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }

    public AuthLogin(String username, String password, Boolean isLoggedIn) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
    }

    public AuthLogin(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public AuthLogin(Long id, String username, String password, String email, String accessToken) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.accessToken = accessToken;
    }

    public AuthLogin(String username, String password, String email, String accessToken) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accessToken = accessToken;
    }

    public AuthLogin(Long id, String username, String password, String email, String accessToken, String refreshToken, String type, Boolean isLoggedIn, Date createdAt, Date expiredAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.type = type;
        this.isLoggedIn = isLoggedIn;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
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

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    @Override
    public String toString() {
        return "AuthLogin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", type='" + type + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", createdAt=" + createdAt +
                ", expiredAt=" + expiredAt +
                '}';
    }

    public static AuthLogin build(User user) {
        return new AuthLogin(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail());
    }

}
