package com.example.sbertaste.mapper;

import com.example.sbertaste.dto.OrderPositionDto;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.model.OrderPositionEntity;
import com.example.sbertaste.service.PizzaService;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class OrderPositionMapper extends CustomMapper<OrderPositionDto, OrderPositionEntity> {

    private final PizzaService pizzaService;

    public OrderPositionMapper(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @Override
    public void mapAtoB(OrderPositionDto orderPositionDto, OrderPositionEntity orderPositionEntity, MappingContext context) {
        try {
            orderPositionEntity.setPizza(pizzaService.getOne(orderPositionDto.getPizzaId()));
        } catch (STNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("No pizza with id %d exists", orderPositionDto.getPizzaId()));
        }
    }

    @Override
    public void mapBtoA(OrderPositionEntity orderPositionEntity, OrderPositionDto orderPositionDto, MappingContext context) {
    }
}
