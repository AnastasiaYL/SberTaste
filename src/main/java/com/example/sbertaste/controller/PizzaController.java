package com.example.sbertaste.controller;

import com.example.sbertaste.dto.PizzaDto;
import com.example.sbertaste.service.PizzaService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaController extends CommonController<PizzaService, PizzaDto> {

    public PizzaController(PizzaService service) {
        super(service);
    }
}
