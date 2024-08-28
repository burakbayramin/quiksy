package com.burak.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int stock;

    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
