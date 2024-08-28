package com.burak.service;

import com.burak.dto.request.LoginRequest;
import com.burak.dto.request.RegisterRequest;
import com.burak.entity.Auth;
import com.burak.exception.AuthException;
import com.burak.exception.ErrorType;
import com.burak.mapper.AuthMapper;
import com.burak.rabbitmq.CreateUser;
import com.burak.rabbitmq.CreateUserProducer;
import com.burak.repository.AuthRepository;
import com.burak.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.burak.entity.State;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final CreateUserProducer createUserProducer;
    public void register(RegisterRequest dto){

        repository.findOptionalByUsername(dto.getUsername())
                .ifPresent(auth->{
                    throw new AuthException(ErrorType.USER_ALREADY_EXISTS);
                });

        Auth auth = AuthMapper.INSTANCE.fromDto(dto);
        auth.setCreatedAt(LocalDateTime.now());
        auth.setState(State.ACTIVE);
        repository.save(auth);

        createUserProducer.converdAndSendMessage(CreateUser.builder()
                .authId(auth.getId())
                .userName(auth.getUsername())
                .build());
    }

    public String login(LoginRequest dto){
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(auth.isEmpty()) throw new AuthException(ErrorType.USERNAME_PASSWORD_ERROR);
        Optional<String> jwtToken = jwtTokenManager.createToken(auth.get().getId());
        if(jwtToken.isEmpty())
            throw new AuthException(ErrorType.TOKEN_ERROR);
        return jwtToken.get();
    }
}
