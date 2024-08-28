package com.burak.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotNull(message = "Auth ID cannot be null")
    private Long authId;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters")
    private String username;

}
