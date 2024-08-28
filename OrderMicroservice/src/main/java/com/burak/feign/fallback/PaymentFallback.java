package com.burak.feign.fallback;


import com.burak.dto.request.PaymentRequest;
import com.burak.feign.PaymentFeignClient;

public class PaymentFallback implements PaymentFeignClient {
    @Override
    public Integer requestOrderPayment(PaymentRequest request) {
        return null;
    }
}
