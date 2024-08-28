package com.burak.dto.response;


public record PurchaseResponse(
        Long productId,
        String name,
        String description,
        double price,
        int quantity
) {
}
