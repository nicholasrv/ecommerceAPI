package com.example.ecommerceapi.controller;

import com.example.ecommerceapi.entity.Category;
import com.example.ecommerceapi.exceptions.BadRequestException;
import com.example.ecommerceapi.exceptions.ResourceNotFoundException;
import com.example.ecommerceapi.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> saveNewCategory(@RequestBody Category category) throws BadRequestException {
        try {
            boolean condition = categoryService.isCategoryDescriptionExists(category.getCategoryDescription());
            if(condition){
                return ResponseEntity.badRequest().body("The category you're trying to save already exists in the database.");
            }
            return ResponseEntity.ok(categoryService.save(category));
        } catch (Exception e){
            throw new BadRequestException("An error occurred while trying to save this category. Please contact our support team for further information.");
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
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId) throws ChangeSetPersister.NotFoundException, SQLException {
        if(categoryService.canDeleteCategory(categoryId)) {
            categoryService.delete(categoryId);
            ResponseEntity.ok("The selected category was successfully deleted.");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("The selected category cannot be deleted, as there are products associated with it in the database.");
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
