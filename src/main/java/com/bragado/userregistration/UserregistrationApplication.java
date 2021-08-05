package com.bragado.userregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class UserregistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserregistrationApplication.class, args);
	}

}
