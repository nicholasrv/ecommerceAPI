package com.example.ecommerceapi.controller;

import com.example.ecommerceapi.custom.CustomersThatOrderTheMostReport;
import com.example.ecommerceapi.custom.MostOrderedProductReport;
import com.example.ecommerceapi.deserializer.DataDeserializer;
import com.example.ecommerceapi.entity.Cart;
import com.example.ecommerceapi.entity.Order;
import com.example.ecommerceapi.exceptions.BadRequestException;
import com.example.ecommerceapi.exceptions.ResourceNotFoundException;
import com.example.ecommerceapi.service.impl.CartServiceImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    //POST
    @PostMapping("/cart/save")
    public ResponseEntity<Cart> saveNewCart(@RequestBody Cart cart) throws BadRequestException {
        try {
            return ResponseEntity.ok(cartService.save(cart));
        } catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException("An error has occurred while trying to save the orders in the cart. Please contact our support team for further information.");
        }
    }

    ///UPDATE/PUT
    @PutMapping("/cart/update")
    public ResponseEntity updateCart(@RequestBody Cart cart) throws SQLException {
        return ResponseEntity.ok(cartService.update(cart));
    }

    // GET
    @RequestMapping(value = "/cart", method = RequestMethod.GET, produces = "application/json")
    public List<Cart> getAllCarts() throws SQLException{
        return cartService.getAllResults();
    }


    // DELETE
    @DeleteMapping("/cart/delete/{id}")
    public ResponseEntity deleteCart(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = cartService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected cart has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The cart with id number " + id + "hasn't been found in the database.");
        }
    }

    //GET BY ID
    @GetMapping("/cart/{id}")
    public ResponseEntity<Optional<Cart>> getCartById(@PathVariable Long id) throws ResourceNotFoundException{
        try{
            Optional<Cart> cart = cartService.searchById(id);
            if(cart.isPresent()){
                return ResponseEntity.ok(cart);
            }
            throw new ResourceNotFoundException("The cart with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching cart with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }

}
