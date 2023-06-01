package com.example.ecommerceapi.controller;

import com.example.ecommerceapi.entity.Product;
import com.example.ecommerceapi.exceptions.BadRequestException;
import com.example.ecommerceapi.exceptions.ResourceNotFoundException;
import com.example.ecommerceapi.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    //POST
    @PostMapping("/product/save")
    public ResponseEntity<?> saveNewProduct(@RequestBody Product product) throws BadRequestException {
        try {
            boolean condition = productService.isProductNameExists(product.getProductName());
             if(condition){
                 return ResponseEntity.badRequest().body("The product you're trying to save already exists in the database.");
             }
            return ResponseEntity.ok(productService.save(product));
        } catch (Exception e){
            throw new BadRequestException("An error occurred while trying to save your product. Please contact our support team for further information.");
        }
    }

    ///UPDATE/PUT
    @PutMapping("/product/update")
    public ResponseEntity updateProduct(@RequestBody Product product) throws SQLException {
        return ResponseEntity.ok(productService.update(product));
    }

    // GET
    @RequestMapping(value = "/product", method = RequestMethod.GET, produces = "application/json")
    public List<Product> getAllProducts() throws SQLException{
        return productService.getAllResults();
    }

    // DELETE
    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = productService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected product has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The product with id number " + id + "hasn't been found in the database.");
        }
    }

    //GET BY ID
    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) throws ResourceNotFoundException{
        try{
            Optional<Product> product = productService.searchById(id);
            if(product.isPresent()){
                return ResponseEntity.ok(product);
            }
            throw new ResourceNotFoundException("The product with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching product with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }
}
