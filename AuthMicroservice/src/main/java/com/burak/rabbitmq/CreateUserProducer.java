package com.burak.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {

    private final RabbitTemplate rabbitTemplate;

    public void converdAndSendMessage(CreateUser createUser){
        rabbitTemplate.convertAndSend("exchange-auth","key-auth",createUser);
    }

}
