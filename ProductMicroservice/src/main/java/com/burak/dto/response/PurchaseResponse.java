package com.burak.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class PurchaseResponse {

    private Long productId;
    private String name;
    private double price;
    private int quantity;
}
