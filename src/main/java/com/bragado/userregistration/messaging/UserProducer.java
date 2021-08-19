package com.bragado.userregistration.messaging;

import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class UserProducer {
    private static final String TOPIC = "userdata-topic";

    private KafkaTemplate<String, Object> kafkaTemplate;
    private ObjectMapper objectMapper;

    public UserProducer(KafkaTemplate<String,Object> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendUser(String key, Object user) {

        ListenableFuture<SendResult<String,Object>> listenableFuture = kafkaTemplate.send(TOPIC, key, user);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String,Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, user, ex);
            }

            @Override
            public void onSuccess(SendResult<String,Object> result) {
                handleSuccess(key, user, result);
            }
        });

    }

    private void handleFailure(String key, Object value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }


    }

    private void handleSuccess(String key, Object value, SendResult<String,Object> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }

}
