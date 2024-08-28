package com.burak.feign;

import com.burak.dto.request.PurchaseRequest;
import com.burak.dto.response.PurchaseResponse;
import com.burak.feign.fallback.ProductFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "product-service", url = "${application.config.product-url}", fallback = ProductFallback.class)
public interface ProductFeignClient {
    @PostMapping("/purchase")
    Optional<List<PurchaseResponse>> purchaseProduct(@RequestBody List<PurchaseRequest> request);
}
