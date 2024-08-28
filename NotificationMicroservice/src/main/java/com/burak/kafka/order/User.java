package com.burak.kafka.order;

public record User(Long id,
                   Long authId,
                   String username,
                   String firstname,
                   String lastname,
                   String email,
                   String phone
){
}

