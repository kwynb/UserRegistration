package com.bragado.userregistration.repositories;

import com.bragado.userregistration.entities.User;
import com.bragado.userregistration.entities.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.List;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long> {

    String SELECT_BY_USERNAME = "SELECT * FROM usertest WHERE username = :username";

    @Query(value=SELECT_BY_USERNAME, nativeQuery = true)
    UserTest findByUsername(@Param("username") String username);

}
