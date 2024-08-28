package com.burak.mapper;

import com.burak.dto.request.UserCreateRequest;
import com.burak.dto.request.UserRequest;
import com.burak.dto.response.UserResponse;
import com.burak.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        if (request == null){
            return null;
        }
        return User.builder()
                .authId(request.getAuthId())
                .username(request.getUsername())
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .phone(request.getPhone())
                .build();
    }

    public User toCreateEntity(UserCreateRequest request) {
        if (request == null){
            return null;
        }
        return User.builder()
                .username(request.getUsername())
                .authId(request.getAuthId())
                .build();
    }

    public UserResponse toResponse(User user) {
        if (user == null){
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getAuthId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhone()
        );
    }
}
