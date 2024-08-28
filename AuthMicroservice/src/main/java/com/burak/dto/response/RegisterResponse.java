package com.burak.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterResponse {
    boolean isRegister;
    String message;
}
