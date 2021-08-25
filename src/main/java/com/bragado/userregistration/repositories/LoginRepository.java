package com.bragado.userregistration.repositories;

import com.bragado.userregistration.entities.Login;
import com.bragado.userregistration.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    @Query(value="SELECT * FROM login WHERE username = :username", nativeQuery = true)
    Login findByUsername(@Param("username") String username);

    @Query(value="SELECT * FROM login WHERE username = :username AND is_logged_in = :isLoggedIn", nativeQuery = true)
    Login findLogin(@Param("username") String username, @Param("isLoggedIn") boolean isLoggedIn);

}
