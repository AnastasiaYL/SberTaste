package com.example.sbertaste.service;

import com.example.sbertaste.dto.PizzaDto;
import com.example.sbertaste.model.PizzaEntity;
import com.example.sbertaste.repository.PizzaRepository;
import org.springframework.stereotype.Service;

@Service
public class PizzaService extends CommonService<PizzaEntity> {

    public PizzaService(PizzaRepository repository) {
        super(repository);
    }
}
