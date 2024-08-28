package com.burak.notification;

import com.burak.payment.PaymentMethod;
public record PaymentNotificationRequest(
        String orderReference,
        double amount,
        PaymentMethod paymentMethod,
        String userFirstname,
        String userLastname,
        String userEmail
) {
}
