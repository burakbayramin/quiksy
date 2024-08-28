package com.burak.dto.resquest;

import com.burak.entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private Long id;

    @NotBlank(message = "Product name is mandatory")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @Min(value = 0, message = "Stock must be 0 or greater")
    private int stock;

    @Min(value = 0, message = "Price must be 0 or greater")
    private double price;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;
}
