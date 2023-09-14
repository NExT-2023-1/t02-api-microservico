package com.example.APImicroservicomodulopratico.DTO;

import java.time.LocalDate;
import java.util.UUID;

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
    private UUID project_cost_center_id;

    @NotNull
    private UUID project_manager;

    @NotNull
    private Status status;

    @NotNull
    private Flag flag;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    public Project toEntity(CostCenter costCenter, User manager){
        return Project.builder()
                .name(this.name)
                .costCenter(costCenter)
                .manager(manager)
                .status(this.status)
                .flag(this.flag)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }
    
}
