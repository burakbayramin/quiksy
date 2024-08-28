package com.burak.dto.request;

import com.burak.dto.response.UserResponse;
import com.burak.entity.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotNull(message = "Order Id cannot be null")
    private Long orderId;

    @Positive(message = "Amount must be greater than zero")
    private double amount;

    @NotNull(message = "Payment Method cannot be null")
    private PaymentMethod paymentMethod;

    @NotBlank(message = "Order Reference cannot be blank")
    private String orderReference;

    @NotNull(message = "User Response cannot be null")
    private UserResponse response;
}
