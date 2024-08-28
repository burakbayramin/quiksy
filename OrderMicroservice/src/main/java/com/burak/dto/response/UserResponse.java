package com.burak.dto.response;

public record UserResponse(
        Long id,
        String username,
        String email,
        String firstname,
        String lastname,
        String phone
) {
}
