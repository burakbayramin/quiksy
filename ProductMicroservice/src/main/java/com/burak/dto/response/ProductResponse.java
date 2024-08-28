package com.burak.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class ProductResponse {
    private Long id;
    private String name;
    private int stock;
    private double price;
    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

}