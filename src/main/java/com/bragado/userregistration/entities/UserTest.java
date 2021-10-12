package com.bragado.userregistration.entities;

import com.bragado.userregistration.components.AttributeEncryptor;
import com.bragado.userregistration.components.UserId;
import com.bragado.userregistration.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;


@Entity
@Table(name="usertest")
@EntityListeners(AuditingEntityListener.class)
public class UserTest {

    @Id
    @UserId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "birthday")
    @Past(message = "Birthday should be valid.")
    @JsonFormat(pattern = "MM/dd/yyyy", shape = JsonFormat.Shape.STRING, timezone = "Asia/Manila")
    private Date birthDay;

    @Column(name = "email")
    @Email(message = "Email should be valid.")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @CreatedDate
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Asia/Manila")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss", timezone = "Asia/Manila")
    private Date lastModified;

    public UserTest() {
    }

    public UserTest(String firstName, String lastName, Date birthDay, String email, String username, String password) {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.birthDay   = birthDay;
        this.email      = email;
        this.username   = username;
        this.password   = password;
        this.role       = "USER";
    }

    public UserTest(Long id, String firstName, String lastName, Date birthDay, String email, String username, String password, String role, Date createdAt, Date lastModified) {
        this.id             = id;
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.birthDay       = birthDay;
        this.email          = email;
        this.username       = username;
        this.password       = password;
        this.role           = role;
        this.createdAt      = createdAt;
        this.lastModified   = lastModified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", lastModified=" + lastModified +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public UserDTO toUserDTO() {
        return new UserDTO(firstName, lastName, birthDay, email, username, password);
    }

}
