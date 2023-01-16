package com.example.sbertaste.controller;

import com.example.sbertaste.dto.CommonDto;
import com.example.sbertaste.dto.PizzaDto;
import com.example.sbertaste.model.CommonEntity;
import com.example.sbertaste.model.PizzaEntity;
import com.example.sbertaste.service.PizzaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizza")
public class PizzaController extends CommonController<PizzaEntity, PizzaDto> {


    public PizzaController(PizzaService service) {
        super(service);
    }

    @Override
    public PizzaEntity mapToEntity(PizzaDto dto) {
        PizzaEntity entity = new PizzaEntity(dto.getName(), dto.getPrice());
        entity.setId(dto.getId());
        return entity;
    }

    @Override
    public PizzaDto mapToDto(PizzaEntity entity) {
        return new PizzaDto(entity.getId(), entity.getName(), entity.getPrice());
    }
}
