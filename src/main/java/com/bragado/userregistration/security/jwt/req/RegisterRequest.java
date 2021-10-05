package com.bragado.userregistration.security.jwt.req;

import com.bragado.userregistration.components.UserId;
import com.bragado.userregistration.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    @Past(message = "Birthday should be valid.")
    private Date birthday;
    @Email(message = "Email should be valid.")
    private String email;
    private String username;
    private String password;
    private String role;


}
