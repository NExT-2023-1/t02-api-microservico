package com.example.APImicroservicomodulopratico.DTO;

import java.time.LocalDate;

import com.example.APImicroservicomodulopratico.Entities.CostCenter;
import com.example.APImicroservicomodulopratico.Entities.Project;
import com.example.APImicroservicomodulopratico.Entities.User;
import com.example.APImicroservicomodulopratico.Enum.Flag;
import com.example.APImicroservicomodulopratico.Enum.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    private CostCenter costCenter;

    @NotNull
    private User manager;

    //private LocalDate starDate;
    //private LocalDate endDate;

    @NotNull
    private Status status;

    @NotNull
    private Flag flag;

    public Project toEntity(){
        return Project.builder()
                .name(this.name)
                .costCenter(this.costCenter)
                .manager(this.manager)
                .status(this.status)
                .flag(this.flag)
                .starDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();
    }

    public Project toEntityUpdateProject(Project project){
        return Project.builder()
                .id(project.getId())
                .name(this.name)
                .costCenter(this.costCenter)
                .manager(this.manager)
                .status(this.status)
                .flag(this.flag)
                .starDate(project.getStarDate())
                .endDate(LocalDate.now())
                //.users(project.getUsers()) //aqui trazemos a lista de users vinculados ao projeto?
                .build();
    }
    
}
