package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
