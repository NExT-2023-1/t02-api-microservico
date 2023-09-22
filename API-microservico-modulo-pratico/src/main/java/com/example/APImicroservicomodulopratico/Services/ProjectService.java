package com.example.APImicroservicomodulopratico.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.APImicroservicomodulopratico.Enum.Flag;
import com.example.APImicroservicomodulopratico.Enum.Role;
import com.example.APImicroservicomodulopratico.Enum.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.APImicroservicomodulopratico.DTO.ProjectDTO;
import com.example.APImicroservicomodulopratico.DTO.ProjectUpdateDTO;
import com.example.APImicroservicomodulopratico.Entities.CostCenter;
import com.example.APImicroservicomodulopratico.Entities.Project;
import com.example.APImicroservicomodulopratico.Entities.User;
import com.example.APImicroservicomodulopratico.Repository.CostCenterRepository;
import com.example.APImicroservicomodulopratico.Repository.ProjectRepository;
import com.example.APImicroservicomodulopratico.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectService {
    
    private final ProjectRepository projectRepository;
    private final CostCenterRepository costCenterRepository;
    private final UserRepository userRepository;

    public Project create(ProjectDTO projectDTO) throws Exception{
        Optional<CostCenter> optionalCostCenter = this.costCenterRepository.findById(projectDTO.getProject_cost_center_id());
        Optional<User> optionalManager = this.userRepository.findById(projectDTO.getProject_manager());

        if (optionalCostCenter.isPresent() && optionalManager.isPresent()) {
            
            if(optionalManager.get().getRole().equals(Role.CONSULTOR)){
                throw new Exception("User is not a manager");
            }
            
            if (projectDTO.getEndDate().isBefore(projectDTO.getStartDate())){
                throw new Exception("End Date can't be less to start date.");
            } 

            CostCenter costCenter = optionalCostCenter.get();
            Optional<Project> optionalProject = this.projectRepository.findByNameAndCostCenterId(projectDTO.getName(), costCenter.getId());
            if (optionalProject.isPresent()){
                throw new Exception("There is already a project with the same name and cost center");
            }
            
            User manager = optionalManager.get();

            Project project = projectDTO.toEntity(costCenter, manager);
            if (project.getStatus().equals(Status.FINESHED)) {
                throw new Exception("Project can't be created with status finished");
            }

            if (!project.getFlag().equals(Flag.GREEN)) {
                throw new Exception("Project can't be created without flag green");
            }
            
            return this.projectRepository.save(project);
            
        }
        throw new Exception("Cost Center or User doesn't exist");
        
    }

    public List<Project> listAll(){
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .collect(Collectors.toList());
    }

    public Project getById(UUID id){
        return this.projectRepository.findById(id).orElse(null);
    }

    public Project update(UUID id, @Valid ProjectUpdateDTO projectUpdateDTO) throws Exception{
        Optional<Project> optionalProject = this.projectRepository.findById(id);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();

            if (projectUpdateDTO.getName() != null) {
                List<Project> projectList = this.projectRepository.findAll();
                for (Project p: projectList){
                    if (p.getName().equals(project.getName()) && p.getCostCenter().getId().equals(project.getCostCenter().getId()) && !p.getId().equals(project.getId())) {
                        throw new Exception("There is a project name with the same cost center");
                    }
                }
                project.setName(projectUpdateDTO.getName());
            }

            if (projectUpdateDTO.getProject_manager() != null) {
                    Optional<User> optionalNewManager = this.userRepository.findById(projectUpdateDTO.getProject_manager());

                    if (optionalNewManager.isPresent()) {
                        if (optionalNewManager.get().getRole().equals(Role.CONSULTOR)) {
                            throw new Exception("User is not a manager");
                        }else{
                            User newManager = optionalNewManager.get();
                            project.setManager(newManager);
                        }
                    }else{
                        throw new Exception("New Manager doesn't exist");
                    }
            }

            if (projectUpdateDTO.getStatus() != null) {
                project.setStatus(projectUpdateDTO.getStatus());
                if (project.getStatus().equals(Status.FINESHED)) {
                    project.setEndDate(LocalDate.now());
                }
            }

            if (projectUpdateDTO.getFlag() != null){
                project.setFlag(projectUpdateDTO.getFlag());
                if (project.getFlag().equals(Flag.RED)) {
                    project.setEndDate(LocalDate.now());
                }
            }

            if (projectUpdateDTO.getEndDate() != null){
                if (projectUpdateDTO.getEndDate().isBefore(project.getStartDate())){
                    throw new Exception("End Date can't be less to start date.");
                }else{
                    project.setEndDate(projectUpdateDTO.getEndDate());
                }
            }  
            
            return this.projectRepository.save(project);
        }else{
            throw new EntityNotFoundException("Cost Center not found.");
        }
    }

    public boolean delete(UUID id){
        Project project = this.projectRepository.findById(id).orElse(null);
        if (project != null) {
            this.projectRepository.delete(project);
            return true;
        }
        return false;
    }

    
}
