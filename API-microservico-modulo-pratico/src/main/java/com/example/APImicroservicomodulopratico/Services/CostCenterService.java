package com.example.APImicroservicomodulopratico.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.APImicroservicomodulopratico.DTO.CostCenterDTO;
import com.example.APImicroservicomodulopratico.Entities.CostCenter;
import com.example.APImicroservicomodulopratico.Repository.CostCenterRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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

    public CostCenter getById(UUID id){
        return this.costCenterRepository.findById(id).orElse(null);
    }

    public CostCenter update(UUID id, @Valid CostCenterDTO costCenterDTO){
        Optional<CostCenter> optionalCostCenter = this.costCenterRepository.findById(id);

        if (optionalCostCenter.isPresent()) {
            CostCenter costCenter = optionalCostCenter.get();

            if (costCenterDTO.getName() != null) {
                costCenter.setName(costCenterDTO.getName());
            }
            return this.costCenterRepository.save(costCenter);
        }else{
            throw new EntityNotFoundException("Cost Center not found.");
        }
    }

    public boolean delete(UUID id){
        CostCenter costCenter = this.costCenterRepository.findById(id).orElse(null);
        if (costCenter != null) {
            this.costCenterRepository.delete(costCenter);
            return true;
        }
        return false;
    }
}
