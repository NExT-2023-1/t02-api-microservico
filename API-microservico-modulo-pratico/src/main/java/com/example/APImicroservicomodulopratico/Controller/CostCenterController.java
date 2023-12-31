package com.example.APImicroservicomodulopratico.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.APImicroservicomodulopratico.DTO.CostCenterDTO;
import com.example.APImicroservicomodulopratico.Entities.CostCenter;
import com.example.APImicroservicomodulopratico.Services.CostCenterService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/costCenter")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CostCenterController {

    private final CostCenterService costCenterService;

    @PostMapping
    public CostCenter create(@RequestBody @Valid CostCenterDTO costCenterDTO) throws Exception{
        CostCenter costCenter = costCenterService.create(costCenterDTO);
        return  costCenter;
    }

    @GetMapping
    public ResponseEntity<List<CostCenter>> listAll(){
        List<CostCenter> costCenters = this.costCenterService.listAll();
        return new ResponseEntity<>(costCenters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostCenter> getById(@PathVariable UUID id){
        CostCenter costCenter = this.costCenterService.getById(id);
        if (costCenter != null){
            return new ResponseEntity<>(costCenter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostCenter> update(@PathVariable UUID id, @RequestBody @Valid CostCenterDTO costCenterDTO){ 
        CostCenter costCenter = costCenterService.update(id, costCenterDTO);
        if (costCenter != null){
            return new ResponseEntity<>(costCenter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (costCenterService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
