package com.example.ecommerceapi.custom;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MostOrderedProductReport {
    String getProductName();
    Double getTotalQuantity();

    @Query("SELECT o.product.productName AS productName, SUM(o.orderAmount) AS totalQuantity " +
            "FROM Order o GROUP BY o.product.productName ORDER BY totalQuantity DESC")
    List<MostOrderedProductReport> getReportByMostOrderedProducts();
}