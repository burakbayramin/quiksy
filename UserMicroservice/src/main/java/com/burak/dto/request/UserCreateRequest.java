package com.burak.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateRequest {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 24, message = "Username must be between 4 and 24 characters")
    private String username;

    Long authId;
}
