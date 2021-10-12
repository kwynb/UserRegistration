package com.bragado.userregistration.messaging;

import com.bragado.userregistration.dto.UserDTO;
import com.bragado.userregistration.entities.UserEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class UserProducer {
    private static final String TOPIC = "userdata-topic";

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public void sendUser(UserEvent userEvent) {

        ListenableFuture<SendResult<String,UserEvent>> listenableFuture = kafkaTemplate.send(TOPIC, userEvent);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String,UserEvent>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(userEvent, ex);
            }

            @Override
            public void onSuccess(SendResult<String,UserEvent> result) {
                handleSuccess(userEvent, result);
            }
        });

    }

    private void handleFailure(UserEvent value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }
    }
    private void handleSuccess(UserEvent value, SendResult<String,UserEvent> result) {
        log.info("Message Sent SuccessFully for the value is {} , partition is {}", value, result.getRecordMetadata().partition());
    }

}
