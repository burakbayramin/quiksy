package com.burak.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineRequest {
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotNull(message = "Order Id cannot be null")
    private Long orderId;

    @NotNull(message = "Product Id cannot be null")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}
