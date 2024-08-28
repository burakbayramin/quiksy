package com.burak.dto;

import com.burak.dto.response.PurchaseResponse;
import com.burak.dto.response.UserResponse;
import com.burak.entity.PaymentMethod;

import java.util.List;

public record OrderConfirmation(
        String orderReference,
        double totalAmount,
        PaymentMethod paymentMethod,
        UserResponse response,
        List<PurchaseResponse> products

) {
}
