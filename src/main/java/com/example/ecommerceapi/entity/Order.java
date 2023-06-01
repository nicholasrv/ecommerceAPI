package com.example.ecommerceapi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User eCommerceUsers;

    @ManyToOne
    private Product product;

    private Double orderAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime orderDate;

    public Order(User eCommerceUsers, Product product, Double orderAmount, LocalDateTime orderDate) {
        this.eCommerceUsers = eCommerceUsers;
        this.product = product;
        this.orderAmount = orderAmount;
        this.orderDate = orderDate;
    }
}
