package com.burak.dto.request;

import com.burak.entity.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Reference cannot be blank")
    private String reference;

    @Positive(message = "Amount must be greater than zero")
    private double amount;

    @NotNull(message = "Payment Method cannot be null")
    private PaymentMethod paymentMethod;

    @NotNull(message = "User Id cannot be null")
    private Long userId;

    @NotEmpty(message = "Products list cannot be empty")
    private List<PurchaseRequest> products;
}
