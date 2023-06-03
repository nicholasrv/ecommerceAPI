package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.entity.Cart;
import com.example.ecommerceapi.entity.Category;
import com.example.ecommerceapi.repository.CartRepository;
import com.example.ecommerceapi.service.eCommerceService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements eCommerceService<Cart> {

    public final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart save(Cart cart) {
        if(cart != null){
            return cartRepository.save(cart);
        }
        return new Cart();
    }

    @Override
    public String update(Cart cart) {
        if(cart != null && cartRepository.findById(cart.getId()).isPresent()){
            cartRepository.saveAndFlush(cart);
            return "The selected cart was successfully updated!";
        }
        return "Sorry, but the selected cart couldn't be updated.";
    }

    @Override
    public List<Cart> getAllResults() throws SQLException {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> searchById(Long id) throws SQLException {
        return cartRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        if(cartRepository.findById(id).isPresent()){
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
