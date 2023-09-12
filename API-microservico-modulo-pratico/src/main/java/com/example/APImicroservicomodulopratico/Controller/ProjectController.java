package com.example.APImicroservicomodulopratico.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.APImicroservicomodulopratico.DTO.ProjectDTO;
import com.example.APImicroservicomodulopratico.Entities.Project;
import com.example.APImicroservicomodulopratico.Services.ProjectServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/project")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {
    
    private final ProjectServices projectServices;

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody @Valid ProjectDTO projectDTO){
        Project project = projectServices.create(projectDTO);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }
}
