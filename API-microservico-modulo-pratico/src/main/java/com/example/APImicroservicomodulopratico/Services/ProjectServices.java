package com.example.APImicroservicomodulopratico.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.APImicroservicomodulopratico.DTO.ProjectDTO;
import com.example.APImicroservicomodulopratico.Entities.Project;
import com.example.APImicroservicomodulopratico.Repository.ProjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServices {
    
    private final ProjectRepository projectRepository;

    public Project create(ProjectDTO projectDTO){
        Project project = projectDTO.toEntity();
        return this.projectRepository.save(project);
    }

    
}
