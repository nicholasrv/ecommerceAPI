package com.example.ecommerceapi.mapper;

import com.example.ecommerceapi.dtos.OrderDTO;
import com.example.ecommerceapi.dtos.ProductDTO;
import com.example.ecommerceapi.dtos.UserDTO;
import com.example.ecommerceapi.entity.Order;
import com.example.ecommerceapi.entity.Product;
import com.example.ecommerceapi.entity.User;

public class Converter {
    private User convertToUserEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUserFirstName(userDTO.getFirstName());
        user.setUserLastName(userDTO.getLastName());
        return user;
    }

    private Product convertToProductEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setProductName(productDTO.getName());
        product.setProductDescription(productDTO.getDescription());
        product.setProductPrice(productDTO.getPrice());
        return product;
    }

    private Order convertToOrderEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setECommerceUsers(convertToUserEntity(orderDTO.getECommerceUsers()));
        order.setProduct(convertToProductEntity(orderDTO.getProduct()));
        order.setOrderAmount(orderDTO.getOrderAmount());
        order.setOrderDate(orderDTO.getOrderDate());
        return order;
    }

    private OrderDTO convertToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setECommerceUsers(convertToUserDTO(order.getECommerceUsers()));
        orderDTO.setProduct(convertToProductDTO(order.getProduct()));
        orderDTO.setOrderAmount(order.getOrderAmount());
        orderDTO.setOrderDate(order.getOrderDate());
        return orderDTO;
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getUserFirstName());
        userDTO.setLastName(user.getUserLastName());
        return userDTO;
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getProductName());
        productDTO.setDescription(product.getProductDescription());
        productDTO.setPrice(product.getProductPrice());
        return productDTO;
    }
}
