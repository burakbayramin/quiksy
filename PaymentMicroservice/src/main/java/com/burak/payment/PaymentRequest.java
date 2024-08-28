package com.burak.payment;

import java.math.BigDecimal;

public record PaymentRequest(
    Integer id,
    double amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    User user
) {
}
