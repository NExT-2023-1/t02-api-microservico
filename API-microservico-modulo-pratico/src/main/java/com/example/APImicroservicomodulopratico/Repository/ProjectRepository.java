package com.example.APImicroservicomodulopratico.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.APImicroservicomodulopratico.Entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID>{
    
}
