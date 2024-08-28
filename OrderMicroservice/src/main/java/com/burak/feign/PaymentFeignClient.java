package com.burak.feign;

import com.burak.dto.request.PaymentRequest;
import com.burak.feign.fallback.PaymentFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "${application.config.payment-url}", fallback = PaymentFallback.class)
public interface PaymentFeignClient {

    @PostMapping
    Integer requestOrderPayment(@RequestBody PaymentRequest request);
}
