package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.entity.Category;
import com.example.ecommerceapi.repository.CategoryRepository;
import com.example.ecommerceapi.service.eCommerceService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements eCommerceService<Category> {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        if(category != null){
            return categoryRepository.save(category);
        }
        return new Category();
    }

    @Override
    public String update(Category category) {
        if(category != null && categoryRepository.findById(category.getId()).isPresent()){
            categoryRepository.saveAndFlush(category);
            return "The selected category was successfully updated!";
        }
        return "Sorry, but the selected category couldn't be updated.";
    }

    @Override
    public List<Category> getAllResults() throws SQLException {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> searchById(Long id) throws SQLException {
        return categoryRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        if(categoryRepository.findById(id).isPresent()){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
