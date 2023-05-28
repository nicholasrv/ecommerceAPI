package com.example.ecommerceapi.controller;

import com.example.ecommerceapi.entity.Product;
import com.example.ecommerceapi.entity.User;
import com.example.ecommerceapi.exceptions.BadRequestException;
import com.example.ecommerceapi.exceptions.ResourceNotFoundException;
import com.example.ecommerceapi.service.impl.ProductServiceImpl;
import com.example.ecommerceapi.service.impl.UserServiceImpl;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    //POST
    @PostMapping("/user/save")
    public ResponseEntity<User> saveNewUser(@RequestBody User user) throws BadRequestException {
        try {
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    ///UPDATE/PUT
    @PutMapping("/user/update")
    public ResponseEntity updateUser(@RequestBody User user) throws SQLException {
        return ResponseEntity.ok(userService.update(user));
    }

    // GET
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public List<User> getAllUsers() throws SQLException{
        return userService.getAllResults();
    }

    // DELETE
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) throws ResourceNotFoundException, SQLException{
        boolean haveItDeleted = userService.delete(id);
        if(haveItDeleted){
            return ResponseEntity.ok("The selected user has been successfully removed from the database!");
        }
        else{
            throw new ResourceNotFoundException("The user with id number " + id + "hasn't been found in the database.");
        }
    }

    //GET BY ID
    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) throws ResourceNotFoundException{
        try{
            Optional<User> user = userService.searchById(id);
            if(user.isPresent()){
                return ResponseEntity.ok(user);
            }
            throw new ResourceNotFoundException("The user with id number " + id + "hasn't been found in the database.");
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error while searching user with id number" + id + ". Please contact our support team for further information/instructions.");
        }
    }
}
