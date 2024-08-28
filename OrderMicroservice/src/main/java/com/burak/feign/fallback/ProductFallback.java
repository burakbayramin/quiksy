package com.burak.feign.fallback;
import com.burak.dto.request.PurchaseRequest;
import com.burak.dto.response.PurchaseResponse;
import com.burak.feign.ProductFeignClient;

import java.util.List;
import java.util.Optional;

public class ProductFallback implements ProductFeignClient {
    @Override
    public Optional<List<PurchaseResponse>> purchaseProduct(List<PurchaseRequest> request) {
        return Optional.empty();
    }
}
