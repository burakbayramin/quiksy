package com.burak.kafka.order;

import com.burak.kafka.payment.PaymentMethod;

import java.util.List;

public record OrderConfirmation(
        String orderReference,
        double totalAmount,
        PaymentMethod paymentMethod,
        User user,
        List<Product> products

) {
}
