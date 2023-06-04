package com.example.ecommerceapi.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private UserDTO eCommerceUsers;
    private ProductDTO product;
    private Double orderAmount;
    private LocalDateTime orderDate;
}
