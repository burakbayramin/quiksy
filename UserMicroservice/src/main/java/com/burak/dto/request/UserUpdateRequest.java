package com.burak.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateRequest {

    String token;

    String email;

    String firstname;

    String lastname;

    String phone;
}
