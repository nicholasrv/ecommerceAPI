package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.entity.Product;
import com.example.ecommerceapi.repository.ProductRepository;
import com.example.ecommerceapi.service.eCommerceService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements eCommerceService<Product> {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        if(product != null){
            productRepository.save(product);
        };
        return new Product();
    }

    @Override
    public String update(Product product) {
        if(product != null && productRepository.findById(product.getId()).isPresent()){
            productRepository.saveAndFlush(product);
            return "The selected product was successfully updated!";
        };
        return "Sorry, but the selected product couldn't be updated.";
    }

    @Override
    public List<Product> getAllResults() throws SQLException {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> searchById(Long id) throws SQLException {
        return productRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        if(productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
