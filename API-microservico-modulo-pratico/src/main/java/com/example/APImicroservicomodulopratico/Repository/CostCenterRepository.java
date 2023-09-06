package com.example.APImicroservicomodulopratico.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.APImicroservicomodulopratico.Entities.CostCenter;

@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, UUID>{
    
}