package com.example.ecommerceapi.entity;

import jakarta.persistence.*;
import lombok.Builder;
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

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productDescription", nullable = false)
    private String productDescription;

    @Column(name = "productPrice", nullable = false)
    private Double productPrice;

    public Product(Category category, String productName, String productDescription, Double productPrice) {
        this.category = category;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }
}
