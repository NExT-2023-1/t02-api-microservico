package com.example.APImicroservicomodulopratico.Service;

import com.example.APImicroservicomodulopratico.DTO.CostCenterDTO;
import com.example.APImicroservicomodulopratico.Entities.CostCenter;
import com.example.APImicroservicomodulopratico.Repository.CostCenterRepository;
import com.example.APImicroservicomodulopratico.Services.CostCenterService;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CostCenterServiceTest {

	@InjectMocks
	private CostCenterService service;

	@Mock
	private CostCenterRepository repository;





	@Test
	void create_sucesso () throws Exception {
		UUID id = UUID.randomUUID();
		String name = "Publicidade";
		CostCenterDTO costCenterDTO = new CostCenterDTO(name);
		CostCenter costCenter = new CostCenter(id, name);

		CostCenter before = new CostCenter();
		before.setName(name);

		Mockito.when(repository.findByName(name)).thenReturn(Optional.empty());
		Mockito.when(repository.save(before)).thenReturn(costCenter);


		CostCenter resultado = service.create(costCenterDTO);

		//Assertation.assertEquals(name, resultado.getName());
	}


	@Test
	void create_exception () throws Exception {
		UUID id = UUID.randomUUID();
		String name = "Publicidade";
		CostCenterDTO costCenterDTO = new CostCenterDTO(name);
		CostCenter costCenter = new CostCenter(id, name);

		Mockito.when(repository.findByName(name)).thenReturn(Optional.of(costCenter));

		//assertThrows(Exception.class, () -> service.create(costCenterDTO));
	}

}
