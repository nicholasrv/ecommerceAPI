package com.example.ecommerceapi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private Cart cart;

    @ManyToOne
    private User eCommerceUsers;

    @ManyToOne
    private Product product;

//    @OneToMany(mappedBy = "order")
//    private List<OrderItem> items = new ArrayList<OrderItem>();

    private Double orderAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime orderDate;

    /// METHOD THAT WILL ADD AN ITEM TO THE ORDER

//    public void addItem (OrderItem item){
//        this.items.add(item);
//        item.setOrder(this);
//    }

    /// CONSTRUCTOR
    public Order(Cart cart, User eCommerceUsers, Product product, Double orderAmount, LocalDateTime orderDate) {
        this.cart = cart;
        this.eCommerceUsers = eCommerceUsers;
        this.product = product;
        this.orderAmount = orderAmount;
        this.orderDate = orderDate;
    }
}
