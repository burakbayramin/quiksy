package com.burak.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
}

