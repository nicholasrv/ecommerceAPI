package com.example.ecommerceapi.custom;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersThatOrderTheMostReport {
    Long getOrderId();
    String getCustomerName();
    Long getTotalQuantity();

    @Query("SELECT o.id AS orderId, CONCAT(u.userFirstName, ' ', u.userLastName) AS customerName, COUNT(o.id) AS totalQuantity " +
            "FROM Order o JOIN o.eCommerceUsers u " +
            "GROUP BY o.id, u.userFirstName, u.userLastName " +
            "ORDER BY totalQuantity DESC")
    List<CustomersThatOrderTheMostReport> getCustomersThatOrderTheMostReport();
}
