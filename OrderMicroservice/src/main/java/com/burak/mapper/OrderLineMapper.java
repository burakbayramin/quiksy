package com.burak.mapper;

import com.burak.dto.request.OrderLineRequest;
import com.burak.dto.response.OrderLineResponse;
import com.burak.entity.Order;
import com.burak.entity.OrderLine;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.getId())
                .productId(request.getProductId())
                .order(
                        Order.builder()
                                .id(request.getOrderId())
                                .build()
                )
                .quantity(request.getQuantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
