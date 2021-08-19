package com.bragado.userregistration;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEncryptableProperties
public class UserregistrationApplication {
//	@Bean
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}
//
//	@Bean
//	public NewTopic topic() {
//		return TopicBuilder.name("user-data-topic")
//				.partitions(1)
//				.replicas(1)
//				.build();
//	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(UserregistrationApplication.class, args);
	}

}
