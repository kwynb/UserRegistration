package com.bragado.userregistration.repositories;

import com.bragado.userregistration.security.jwt.res.JWTResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JWTResponseRepository extends JpaRepository<JWTResponse,Long> {

}
