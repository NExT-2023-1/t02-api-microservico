package com.example.APImicroservicomodulopratico.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.APImicroservicomodulopratico.DTO.CostCenterDTO;
import com.example.APImicroservicomodulopratico.Entities.CostCenter;
import com.example.APImicroservicomodulopratico.Repository.CostCenterRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CostCenterService {
    
    private final CostCenterRepository costCenterRepository;

    public CostCenter create(CostCenterDTO costCenterDTO){
        CostCenter costCenter = costCenterDTO.toEntity();
        return this.costCenterRepository.save(costCenter);
    }

    public List<CostCenter> listAll(){
        List<CostCenter> costCenters = costCenterRepository.findAll();
        return costCenters.stream()
                .collect(Collectors.toList());
    }
}
