package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.custom.MostOrderedProductReport;
import com.example.ecommerceapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.product.productName AS productName, SUM(o.orderAmount) AS totalQuantity " +
            "FROM Order o GROUP BY o.product.productName ORDER BY totalQuantity DESC")
    List<MostOrderedProductReport> getReportByMostOrderedProducts();

}
