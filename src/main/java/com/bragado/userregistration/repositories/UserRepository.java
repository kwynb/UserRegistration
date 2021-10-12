package com.bragado.userregistration.repositories;

import com.bragado.userregistration.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String SELECT_BY_FIRSTNAME  = "SELECT * FROM user WHERE firstname = :firstname";
    String SELECT_BY_LASTNAME   = "SELECT * FROM user WHERE lastname = :lastname";
    String SELECT_BY_NAME       = "SELECT * FROM user WHERE firstname = :firstname AND lastname = :lastname";
    String SELECT_BY_EMAIL      = "SELECT * FROM user WHERE email = :email";
    String SELECT_BY_USERNAME   = "SELECT * FROM user WHERE username = :username";

    @Query(value=SELECT_BY_FIRSTNAME, nativeQuery = true)
    List<User> findByFirstName(@Param("firstname") String firstname);

    @Query(value=SELECT_BY_LASTNAME, nativeQuery = true)
    List<User> findByLastName(@Param("lastname") String lastname);

    @Query(value=SELECT_BY_NAME, nativeQuery = true)
    User findByName(@Param("firstname") String firstname,
                    @Param("lastname") String lastname);

    @Query(value=SELECT_BY_EMAIL, nativeQuery = true)
    User findByEmail(@Param("email") @Email String email);

    @Query(value=SELECT_BY_USERNAME, nativeQuery = true)
    User findByUsername(@Param("username") String username);

}
