package com.example.sbertaste.controller;

import com.example.sbertaste.dto.pizza.PizzaRequestDto;
import com.example.sbertaste.dto.pizza.PizzaResponseDto;
import com.example.sbertaste.model.PizzaEntity;
import com.example.sbertaste.service.PizzaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizza")
public class PizzaController extends CommonController<PizzaEntity, PizzaRequestDto, PizzaResponseDto> {
    public PizzaController(PizzaService service) {
        super(service);
    }

}
