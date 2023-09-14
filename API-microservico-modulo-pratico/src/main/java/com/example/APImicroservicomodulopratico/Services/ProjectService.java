package com.example.APImicroservicomodulopratico.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

        //Mais na FRENTE, fzer a verificar se o id de gerente é um GERENTE.

        if (optionalCostCenter.isPresent() && optionalManager.isPresent()) {
            CostCenter costCenter = optionalCostCenter.get();
            User manager = optionalManager.get();

            Project project = projectDTO.toEntity(costCenter, manager);
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

    public Project update(UUID id, @Valid ProjectUpdateDTO projectUpdateDTO){
        Optional<Project> optionalProject = this.projectRepository.findById(id);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();

            if (projectUpdateDTO.getName() != null) {
                project.setName(projectUpdateDTO.getName());
            }

            if (projectUpdateDTO.getProject_manager() != null) {
                //Fazemos uma verificacao se existe o gerente novo, BUSCANDO o seu ID
                //Se houver esse novo gerente, modificamos o antigo, pelo novo, se não lanca a excecao;
                Optional<User> optionalNewManager = this.userRepository.findById(projectUpdateDTO.getProject_manager());


                //da o STATUS OK, mas não atualiza o gerente!
                if (optionalNewManager.isPresent()) {
                    //se o novo ID tiver presente, vamos passala a um Usuario (retirar o Optional)
                    User newManager = optionalNewManager.get();
                    //setamos o novo manager dentro do projeto.
                    project.setManager(newManager);
                }else{
                    throw new EntityNotFoundException("New Manager doesn't exist");
                }
            }
            if (projectUpdateDTO.getStatus() != null) {
                project.setStatus(projectUpdateDTO.getStatus());
            }

            if (projectUpdateDTO.getFlag() != null){
                project.setFlag(projectUpdateDTO.getFlag());
            }

            if (projectUpdateDTO.getEndDate() != null){
                project.setEndDate(projectUpdateDTO.getEndDate());
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
