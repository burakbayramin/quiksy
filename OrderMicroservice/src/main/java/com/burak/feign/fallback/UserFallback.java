package com.burak.feign.fallback;

import com.burak.dto.response.UserResponse;
import com.burak.feign.UserFeignClient;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserFallback implements UserFeignClient {

    @Override
    public Optional<UserResponse> getUser(Long userId) {
        return Optional.empty();
    }
}
