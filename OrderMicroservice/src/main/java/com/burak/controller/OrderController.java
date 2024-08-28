package com.burak.controller;

import com.burak.constants.MessageConstants;
import com.burak.dto.request.OrderRequest;
import com.burak.dto.response.OrderResponse;
import com.burak.dto.response.StatusResponse;
import com.burak.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.burak.constants.PathConstants.ORDERS;
import static com.burak.constants.PathConstants.ORDER_ID;

@RestController
@RequestMapping(ORDERS)
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<StatusResponse> createOrder(@RequestBody @Valid OrderRequest request) {
        orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StatusResponse(MessageConstants.STATUS_201, MessageConstants.MESSAGE_201));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(this.orderService.findAllOrders());
    }

    @GetMapping(ORDER_ID)
    public ResponseEntity<OrderResponse> findById(@PathVariable("orderId") Integer orderId) {
        return ResponseEntity.ok(this.orderService.findById(orderId));
    }
}
