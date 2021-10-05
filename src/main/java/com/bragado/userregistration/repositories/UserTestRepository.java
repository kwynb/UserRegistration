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
//
//    @Query(value="SELECT * FROM user WHERE firstname = :firstname", nativeQuery = true)
//    List<User> findByFirstName(@Param("firstname") String firstname);
//
//    @Query(value="SELECT * FROM user WHERE lastname = :lastname", nativeQuery = true)
//    List<User> findByLastName(@Param("lastname") String lastname);
//
//    @Query(value="SELECT * FROM user WHERE firstname = :firstname AND lastname = :lastname", nativeQuery = true)
//    User findByName(@Param("firstname") String firstname, @Param("lastname") String lastname);
//
//    @Query(value="SELECT * FROM user WHERE email = :email", nativeQuery = true)
//    User findByEmail(@Param("email") @Email String email);

    @Query(value="SELECT * FROM usertest WHERE username = :username", nativeQuery = true)
    UserTest findByUsername(@Param("username") String username);

}
