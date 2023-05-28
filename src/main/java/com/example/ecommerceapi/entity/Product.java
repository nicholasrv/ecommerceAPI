package com.example.ecommerceapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Category category;

    private String name;
    private String description;
    private Double price;

    public Product(Category category, String name, String description, Double price) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
