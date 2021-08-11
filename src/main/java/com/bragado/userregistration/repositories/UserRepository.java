package com.bragado.userregistration.repositories;

import com.bragado.userregistration.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value="SELECT * FROM userdb WHERE email = :email", nativeQuery = true)
    User findByEmail(@Param("email") @Email String email);

}
