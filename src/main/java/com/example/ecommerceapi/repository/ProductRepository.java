package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.entity.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsProductByProductName(String productName);

}
