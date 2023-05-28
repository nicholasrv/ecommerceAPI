package com.example.ecommerceapi.controller;

import com.example.ecommerceapi.entity.Category;
import com.example.ecommerceapi.exceptions.BadRequestException;
import com.example.ecommerceapi.exceptions.ResourceNotFoundException;
import com.example.ecommerceapi.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    //POST
    @PostMapping("/category/save")
    public ResponseEntity<Category> saveNewCategory(@RequestBody Category category) throws BadRequestException {
        try {
            return ResponseEntity.ok(categoryService.save(category));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    ///UPDATE/PUT
    @PutMapping("/category/update")
    public ResponseEntity updateCategory(@RequestBody Category category) throws SQLException {
        return ResponseEntity.ok(categoryService.update(category));
    }

    // GET
    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
    public List<Category> getAllCategories() throws SQLException{
        return categoryService.getAllResults();
    }

    // DELETE
    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = categoryService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected category has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The category with id number " + id + "hasn't been found in the database.");
        }
    }

    //GET BY ID
    @GetMapping("/category/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long id) throws ResourceNotFoundException{
        try{
            Optional<Category> category = categoryService.searchById(id);
            if(category.isPresent()){
                return ResponseEntity.ok(category);
            }
            throw new ResourceNotFoundException("The category with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching category with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }
}
