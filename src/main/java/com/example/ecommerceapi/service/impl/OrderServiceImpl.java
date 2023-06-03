package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.custom.CustomersThatOrderTheMostReport;
import com.example.ecommerceapi.custom.MostOrderedProductReport;
import com.example.ecommerceapi.entity.Order;
import com.example.ecommerceapi.repository.OrderRepository;
import com.example.ecommerceapi.service.eCommerceService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements eCommerceService<Order> {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        if(order != null){
            return orderRepository.save(order);
        };
        return new Order();
    }

    @Override
    public String update(Order order) {
        if(order != null && orderRepository.findById(order.getId()).isPresent()){
            orderRepository.saveAndFlush(order);
            return "The selected order was successfully updated!";
        };
        return "Sorry, but the selected order couldn't be updated";
    }

    @Override
    public List<Order> getAllResults() throws SQLException {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> searchById(Long id) throws SQLException {
        return orderRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        if (orderRepository.findById(id).isPresent()){
            orderRepository.deleteById(id);
            return true;
        };
        return false;
    }

    public List<MostOrderedProductReport> getReportByMostOrderedProducts() {
        return orderRepository.getReportByMostOrderedProducts();
    }

    public List<CustomersThatOrderTheMostReport> getCustomersThatOrderTheMostReport(){
        return orderRepository.getCustomersThatOrderTheMostReport();
    }
}
