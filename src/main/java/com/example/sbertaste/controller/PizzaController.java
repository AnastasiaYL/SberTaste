package com.example.sbertaste.controller;

import com.example.sbertaste.dto.PizzaDto;
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

}
