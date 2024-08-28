package com.burak.service;

import com.burak.dto.request.OrderLineRequest;
import com.burak.dto.response.OrderLineResponse;
import com.burak.mapper.OrderLineMapper;
import com.burak.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    public void saveOrderLine(OrderLineRequest request) {
        var order = orderLineMapper.toOrderLine(request);
        orderLineRepository.save(order);
    }
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
