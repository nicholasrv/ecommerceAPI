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
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    private User ecommerceUser;

    @ManyToOne
    private Product product;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime orderDate;

    public Order(User eccommerceUser, Product product, LocalDateTime orderDate) {
        this.ecommerceUser = eccommerceUser;
        this.product = product;
        this.orderDate = orderDate;
    }
}
