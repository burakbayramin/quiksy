package com.burak.mapper;

import com.burak.dto.response.ProductResponse;
import com.burak.dto.response.PurchaseResponse;
import com.burak.dto.resquest.ProductRequest;
import com.burak.entity.Category;
import com.burak.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        if (request == null){
            return null;
        }
        return Product.builder()
                .id(request.getId())
                .name(request.getName())
                .stock(request.getStock())
                .price(request.getPrice())
                .category(
                        Category.builder()
                                .id(request.getCategoryId())
                                .build()
                )
                .build();
    }

    public ProductResponse toResponse(Product product) {
        if (product == null){
            return null;
        }
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getStock(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()

        );
    }

    public PurchaseResponse toPurchaseResponse(Product product, int quantity) {
        return new PurchaseResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                quantity
        );
    }
}
