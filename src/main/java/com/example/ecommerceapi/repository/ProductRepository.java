package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.entity.Category;
import com.example.ecommerceapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsProductByProductName(String productName);

    boolean existsByCategory(Category category);

}
