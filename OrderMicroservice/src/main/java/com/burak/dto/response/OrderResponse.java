package com.burak.dto.response;

import com.burak.entity.PaymentMethod;

public record OrderResponse(
    Long id,
    String reference,
    double amount,
    PaymentMethod paymentMethod,
    Long userId
) {

}
