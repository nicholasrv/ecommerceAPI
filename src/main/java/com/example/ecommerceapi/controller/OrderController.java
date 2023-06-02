package com.example.ecommerceapi.controller;


import com.example.ecommerceapi.custom.MostOrderedProductReport;
import com.example.ecommerceapi.deserializer.DataDeserializer;
import com.example.ecommerceapi.entity.Category;
import com.example.ecommerceapi.entity.Order;
import com.example.ecommerceapi.exceptions.BadRequestException;
import com.example.ecommerceapi.exceptions.ResourceNotFoundException;
import com.example.ecommerceapi.service.impl.CategoryServiceImpl;
import com.example.ecommerceapi.service.impl.OrderServiceImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    //POST
    @PostMapping("/order/save")
    public ResponseEntity<?> saveNewOrder(@RequestBody @JsonDeserialize(using = DataDeserializer.Deserializer.class) Order order) throws BadRequestException {
        try {
            double orderAmountValue = order.getOrderAmount();
            if (orderAmountValue <= 0){
                return ResponseEntity.badRequest().body("The order amount cannot be zero or negative, it has to be positive.");
            }
            return ResponseEntity.ok("Order placed successfully!" + orderService.save(order));
        } catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException("An error has occurred while trying to create your order. Please contact our support team for further information.");
        }
    }

    ///UPDATE/PUT
    @PutMapping("/order/update")
    public ResponseEntity updateOrder(@RequestBody Order order) throws SQLException {
        return ResponseEntity.ok(orderService.update(order));
    }

    // GET
    @RequestMapping(value = "/order", method = RequestMethod.GET, produces = "application/json")
    public List<Order> getAllOrders() throws SQLException{
        return orderService.getAllResults();
    }

    //GET A LIST FOR THE MOST ORDERED PRODUCTS
    @RequestMapping(value = "/mostordered", method = RequestMethod.GET, produces = "application/json")
    public List<MostOrderedProductReport> getMostOrderedProducts() throws SQLException{
        return orderService.getReportByMostOrderedProducts();
    }

    // DELETE
    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = orderService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected order has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The order with id number " + id + "hasn't been found in the database.");
        }
    }

    //GET BY ID
    @GetMapping("/order/{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Long id) throws ResourceNotFoundException{
        try{
            Optional<Order> order = orderService.searchById(id);
            if(order.isPresent()){
                return ResponseEntity.ok(order);
            }
            throw new ResourceNotFoundException("The order with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching order with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }
}
