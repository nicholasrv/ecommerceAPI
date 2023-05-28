package com.example.ecommerceapi.service.impl;

import com.example.ecommerceapi.entity.User;
import com.example.ecommerceapi.repository.UserRepository;
import com.example.ecommerceapi.service.eCommerceService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements eCommerceService<User> {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        if(user != null){
            return userRepository.save(user);
        };
        return new User();
    }

    @Override
    public String update(User user) {
        if(user != null && userRepository.findById(user.getId()).isPresent()){
            userRepository.saveAndFlush(user);
            return "The selected user was successfully updated!";
        };
        return "Sorry but the selected user couldn't be updated.";
    }

    @Override
    public List<User> getAllResults() throws SQLException {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> searchById(Long id) throws SQLException {
        return userRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
