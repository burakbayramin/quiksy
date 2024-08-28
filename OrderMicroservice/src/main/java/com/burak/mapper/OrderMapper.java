package com.burak.mapper;

import com.burak.dto.request.OrderRequest;
import com.burak.dto.response.OrderResponse;
import com.burak.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        if (request == null) {
            return null;
        }
        return Order.builder()
                .id(request.getId())
                .reference(request.getReference())
                .paymentMethod(request.getPaymentMethod())
                .userId(request.getUserId())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getUserId()
        );
    }
}
