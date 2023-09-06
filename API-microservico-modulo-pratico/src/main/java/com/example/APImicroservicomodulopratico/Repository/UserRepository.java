package com.example.APImicroservicomodulopratico.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.APImicroservicomodulopratico.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    
}
