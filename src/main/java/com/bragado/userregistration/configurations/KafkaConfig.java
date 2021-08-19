package com.bragado.userregistration.configurations;


import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.UserEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.List;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, UserEvent> kafkaTemplate(ProducerFactory<String, UserEvent> producerConfig) {
        return new KafkaTemplate<>(producerConfig);
    }

}
