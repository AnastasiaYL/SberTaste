package com.example.sbertaste.controller;

import com.example.sbertaste.dto.pizza.PizzaRequestDto;
import com.example.sbertaste.dto.pizza.PizzaResponseDto;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.model.PizzaEntity;
import com.example.sbertaste.service.PizzaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizza")
@Tag(name = "Pizza API", description = "Operations on a pizza")
public class PizzaController extends CommonController<PizzaEntity, PizzaRequestDto, PizzaResponseDto> {
    public PizzaController(PizzaService service) {
        super(service);
    }

    @Override
    @SecurityRequirement(name = "Bearer Authentication")
    public PizzaResponseDto create(PizzaRequestDto dto) {
        return super.create(dto);
    }

    @Override
    @SecurityRequirement(name = "Bearer Authentication")
    public PizzaResponseDto update(PizzaRequestDto dto, Integer id) throws STNotFoundException {
        return super.update(dto, id);
    }

    @Override
    @SecurityRequirement(name = "Bearer Authentication")
    public void deleteById(Integer id) {
        super.deleteById(id);
    }
}
