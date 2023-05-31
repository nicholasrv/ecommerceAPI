package com.example.ecommerceapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "categoryDescription", nullable = false)
    private String categoryDescription;

    public Category(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
