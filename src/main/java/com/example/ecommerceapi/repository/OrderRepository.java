package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.custom.CustomersThatOrderTheMostReport;
import com.example.ecommerceapi.custom.MostOrderedProductReport;
import com.example.ecommerceapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.product.productName AS productName, SUM(o.orderAmount) AS totalQuantity " +
            "FROM Order o GROUP BY o.product.productName ORDER BY totalQuantity DESC")
    List<MostOrderedProductReport> getReportByMostOrderedProducts();

    @Query("SELECT o.id AS orderId, CONCAT(u.userFirstName, ' ', u.userLastName) AS customerName, COUNT(o.id) AS totalQuantity " +
            "FROM Order o JOIN o.eCommerceUsers u " +
            "GROUP BY o.id, u.userFirstName, u.userLastName " +
            "ORDER BY totalQuantity DESC")
    List<CustomersThatOrderTheMostReport> getCustomersThatOrderTheMostReport();

}
