package com.example.sbertaste.service;

import com.example.sbertaste.dto.PizzaDto;
import com.example.sbertaste.model.PizzaEntity;
import com.example.sbertaste.repository.PizzaRepository;
import org.springframework.stereotype.Service;

@Service
public class PizzaService extends CommonService<PizzaRepository, PizzaEntity, Integer, PizzaDto> {


    public PizzaService(PizzaRepository repository) {
        super(repository);
    }

    @Override
    public PizzaEntity mapToEntity(PizzaDto dto) {
        PizzaEntity pizzaEntity = new PizzaEntity(dto.getName(), dto.getPrice());
        pizzaEntity.setId(dto.getId());

        return pizzaEntity;
    }

    @Override
    public PizzaDto mapToDto(PizzaEntity entity) {
        return new PizzaDto(entity.getId(), entity.getName(), entity.getPrice());
    }
}
