package com.bragado.userregistration.repositories;

import com.bragado.userregistration.entities.AuthLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthLogin, Long> {
}
