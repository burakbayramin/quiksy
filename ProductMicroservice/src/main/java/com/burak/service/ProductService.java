package com.burak.service;

import com.burak.dto.response.ProductResponse;
import com.burak.dto.response.PurchaseResponse;
import com.burak.dto.resquest.ProductRequest;
import com.burak.dto.resquest.PurchaseRequest;
import com.burak.entity.Product;
import com.burak.exception.PurchaseException;
import com.burak.exception.ResourceNotFoundException;
import com.burak.mapper.ProductMapper;
import com.burak.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public void createProduct(ProductRequest request) {
        productRepository.save(productMapper.toEntity(request));
    }

    public ProductResponse getProduct(Long productId) {
        return productMapper.toResponse(productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Book", "id", productId)));
    }

    public List<ProductResponse> getAllProducts() {
        try {
            return productRepository.findAll()
                    .stream()
                    .map(productMapper::toResponse)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to fetch products from database", ex);
        }
    }

    @Transactional
    public boolean updateProduct(ProductRequest request) {
        boolean isUpdated = false;

        if (productRepository.findById(request.getId()).isPresent()) {
            Product temp = productRepository.findById(request.getId()).get();
            temp.setName(request.getName());
            temp.setPrice(request.getPrice());
            temp.setStock(request.getStock());
            productRepository.save(temp);
            isUpdated = true;
        } else {
            throw new ResourceNotFoundException("Product", "ID", request.getId());
        }
        return isUpdated;
    }

    @Transactional
    public boolean deleteProduct(Long productId) {
        boolean isDeleted = false;

        if (productRepository.findById(productId).isPresent()) {
            productRepository.deleteById(productId);
            isDeleted = true;
        } else {
            throw new ResourceNotFoundException("Book", "ID", productId);
        }
        return isDeleted;
    }

    @Transactional(rollbackFor = PurchaseException.class)
    public List<PurchaseResponse> purchase(List<PurchaseRequest> request) {

        var productsIds = request.stream()
                .map(PurchaseRequest::getProductId)
                .toList();

        var storedProducts = productRepository.findAllByIdInOrderById(productsIds);

        if (productsIds.size() != storedProducts.size()) {
            throw new PurchaseException("Some products are not found in the database");
        }

        var sortedRequest = request.stream()
                .sorted(Comparator.comparing(PurchaseRequest::getProductId))
                .toList();

        var purchasedProducts = new ArrayList<PurchaseResponse>();

        for (int i = 0; i< storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);

            if (product.getStock() < productRequest.getQuantity()) {
                throw new PurchaseException("Not enough stock for product with ID: " + product.getId());
            }

            var newStock = product.getStock() - productRequest.getQuantity();

            product.setStock(newStock);

            productRepository.save(product);

            purchasedProducts.add(productMapper.toPurchaseResponse(product, productRequest.getQuantity()));
        }
        return purchasedProducts;
    }
}
