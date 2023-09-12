package com.example.APImicroservicomodulopratico.DTO;

import com.example.APImicroservicomodulopratico.Entities.CostCenter;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostCenterDTO {
    
    @NotBlank
    private String name;

    public CostCenter toEntity(){
        return CostCenter.builder()
                .name(this.name)
                .build();
    }
}
