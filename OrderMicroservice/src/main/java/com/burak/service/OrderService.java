package com.burak.service;

import com.burak.dto.OrderConfirmation;
import com.burak.dto.request.OrderLineRequest;
import com.burak.dto.request.OrderRequest;
import com.burak.dto.request.PaymentRequest;
import com.burak.dto.request.PurchaseRequest;
import com.burak.dto.response.OrderResponse;
import com.burak.exception.BusinessException;
import com.burak.feign.PaymentFeignClient;
import com.burak.feign.ProductFeignClient;
import com.burak.feign.UserFeignClient;
import com.burak.kafka.OrderProducer;
import com.burak.mapper.OrderMapper;
import com.burak.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserFeignClient userFeignClient;
    private final ProductFeignClient productFeignClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentFeignClient paymentFeignClient;

    @Transactional
    public void createOrder(OrderRequest request) {
        var user = this.userFeignClient.getUser(request.getUserId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchasedProducts = productFeignClient.purchaseProduct(request.getProducts())
                .orElseThrow(() -> new BusinessException("Cannot create order. Product not found"));

        var order = this.orderRepository.save(orderMapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.getProducts()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.getProductId(),
                            purchaseRequest.getQuantity()
                    )
            );
        }
        var paymentRequest = new PaymentRequest(
                order.getId(),
                request.getAmount(),
                request.getPaymentMethod(),
                order.getReference(),
                user
        );
        paymentFeignClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.getReference(),
                        request.getAmount(),
                        request.getPaymentMethod(),
                        user,
                        purchasedProducts
                )
        );
    }

    public List<OrderResponse> findAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(this.orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return this.orderRepository.findById(id)
                .map(this.orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
