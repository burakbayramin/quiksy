package com.burak.controller;

import com.burak.dto.response.OrderLineResponse;
import com.burak.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.burak.constants.PathConstants.ORDERLINES;

@RestController
@RequestMapping(ORDERLINES)
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderLineService.findAllByOrderId(orderId));
    }
}
