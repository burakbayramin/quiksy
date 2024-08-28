package com.burak.feign;

import com.burak.dto.response.UserResponse;
import com.burak.feign.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user-service", url = "${application.config.user-url}", fallback = UserFallback.class)
public interface UserFeignClient {

    @GetMapping("/{userId}")
    Optional<UserResponse> getUser(@PathVariable Long userId);
}
