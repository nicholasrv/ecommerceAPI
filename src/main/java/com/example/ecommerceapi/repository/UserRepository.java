package com.example.ecommerceapi.repository;

import com.example.ecommerceapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
