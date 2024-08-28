package com.burak.controller;

import com.burak.constants.MessageConstants;
import com.burak.dto.response.ProductResponse;
import com.burak.dto.response.PurchaseResponse;
import com.burak.dto.response.StatusResponse;
import com.burak.dto.resquest.ProductRequest;
import com.burak.dto.resquest.PurchaseRequest;
import com.burak.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

import static com.burak.constants.PathConstants.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(PRODUCTS)
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<StatusResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        productService.createProduct(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new StatusResponse(MessageConstants.STATUS_201, MessageConstants.MESSAGE_201));
    }

    @GetMapping("/{productId}")
    @Cacheable(value = "products", key = "#productId")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> list = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping
    @CachePut(value = "products", key = "#request.productId")
    public ResponseEntity<StatusResponse> updateProduct(@Valid @RequestBody ProductRequest request) {
        boolean isUpdated = productService.updateProduct(request);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new StatusResponse(MessageConstants.STATUS_200, MessageConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new StatusResponse(MessageConstants.STATUS_417, MessageConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/{productId}")
    @CacheEvict(value = "products", key = "#productId")
    public ResponseEntity<StatusResponse> deleteProduct(@PathVariable Long productId) {
        boolean isDeleted = productService.deleteProduct(productId);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new StatusResponse(MessageConstants.STATUS_200, MessageConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new StatusResponse(MessageConstants.STATUS_417, MessageConstants.MESSAGE_417_DELETE));
        }
    }

    @PostMapping(PURCHASE)
    @CacheEvict(value = "products", allEntries = true)
    public ResponseEntity<List<PurchaseResponse>> purchaseProduct(@RequestBody List<PurchaseRequest> request) {
        return ResponseEntity.ok(productService.purchase(request));
    }
}
