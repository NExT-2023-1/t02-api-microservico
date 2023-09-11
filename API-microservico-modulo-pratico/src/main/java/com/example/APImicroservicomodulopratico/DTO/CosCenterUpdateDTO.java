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
public class CosCenterUpdateDTO {
    
    @NotBlank
    private String name;

    public CostCenter toEntityUpdate(CostCenter costCenter){
        return CostCenter.builder()
                .id(costCenter.getId())
                .name(this.name)
                .build();
    }
}
