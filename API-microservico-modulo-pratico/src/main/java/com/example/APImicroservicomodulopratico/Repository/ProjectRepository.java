package com.example.APImicroservicomodulopratico.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.APImicroservicomodulopratico.Entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID>{
    Optional<Project> findByName(String name);
    @Query("Select P Project P Where P.name = :name and P.costCenter.id = :costCenterId")
    Optional<Project> findByNameAndCostCenterId(@Param("name") String name, @Param("costCenterId") UUID costCenterId);
}
