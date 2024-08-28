package com.burak.kafka.payment;


public record PaymentConfirmation(
        String orderReference,
        double amount,
        PaymentMethod paymentMethod,
        String userFirstname,
        String userLastname,
        String userEmail
) {
}
