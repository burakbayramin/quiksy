package com.burak.controller;

import com.burak.constants.MessageConstants;
import com.burak.dto.request.LoginRequest;
import com.burak.dto.request.RegisterRequest;
import com.burak.dto.response.LoginResponse;
import com.burak.dto.response.RegisterResponse;
import com.burak.dto.response.StatusResponse;
import com.burak.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static com.burak.constants.PathConstants.*;

@RestController
@RequestMapping(AUTHSERVICE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @CrossOrigin("*")
    @PostMapping(REGISTER)
    public ResponseEntity<StatusResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request){
        if(!request.getPassword().equals(request.getConfirmPassword()))
            throw new RuntimeException("Passwords do not match");
        authService.register(request);
        return ResponseEntity.ok(StatusResponse.<RegisterResponse>builder()
                .responseCode(200)
                .data(RegisterResponse.builder()
                        .isRegister(true)
                        .message("Registration completed successfully")
                        .build())
                .build());
    }

    @CrossOrigin("*")
    @PostMapping(LOGIN)
    public ResponseEntity<StatusResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest dto){
        String token = authService.login(dto);
        return ResponseEntity.ok(StatusResponse.<LoginResponse>builder()
                .responseCode(200)
                .data(LoginResponse.builder()
                        .isLogin(true)
                        .token(token)
                        .build())
                .build());
    }
}
