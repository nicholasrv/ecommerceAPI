//package com.example.ecommerceapi.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "OrderItems")
//public class OrderItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    // BIDIRECTIONAL RELATIONSHIP WITH ORDER
//    @ManyToOne
//    @JoinColumn(name = "fk_order")
//    private Order order;
//
//    // UNIDIRECTIONAL RELATIONSHIP WITH PRODUCT
//    @ManyToOne
//    private Product product;
//
//    // ITEM ATTRIBUTES
//    private Integer quantity;
//    private Double itemPrice;
//
//}
