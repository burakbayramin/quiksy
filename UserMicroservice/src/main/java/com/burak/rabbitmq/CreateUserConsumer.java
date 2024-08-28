package com.burak.rabbitmq;


import com.burak.dto.request.UserCreateRequest;
import com.burak.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserService userService;
    @RabbitListener(queues = "queue-auth-create-user")
    public void createUserFromQueue(CreateUser createUser){
        userService.createUser(UserCreateRequest.builder()
                        .username(createUser.getUsername())
                        .authId(createUser.getAuthId())
                .build());
    }

}
