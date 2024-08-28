package com.burak.feign;

import com.burak.dto.request.UserCreateRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="user-feign", url = "${application.config.user-url}")
public interface UserFeignClient {

    @PostMapping
    ResponseEntity<Void> createUser(@Valid @RequestBody UserCreateRequest request);
}
