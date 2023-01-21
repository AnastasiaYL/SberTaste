package com.example.sbertaste.controller;

import com.example.sbertaste.dto.OrderPositionDto;
import com.example.sbertaste.dto.order.OrderDetailsDto;
import com.example.sbertaste.dto.order.OrderDto;
import com.example.sbertaste.mapper.OrikaBeanMapper;
import com.example.sbertaste.model.OrderEntity;
import com.example.sbertaste.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;
    private final OrikaBeanMapper mapper;


    public OrderController(OrderService service, OrikaBeanMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/cart/position")
    @Operation(description = "Get all objects", method = "GetAll")
    public List<OrderPositionDto> getCart() {
        return service.getPositions();
    }

    @PostMapping("/cart/position")
    @Operation(description = "Create object", method = "Create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPositionDto create(@NotNull @RequestBody @Validated OrderPositionDto orderPositionDto) {
        return service.addPosition(orderPositionDto);
    }

    @PostMapping("/place")
    public OrderDto placeOrder(OrderDetailsDto dto) {
        return mapper.map(
                service.placeOrder(mapper.map(dto, OrderEntity.class)),
                OrderDto.class);
    }
}
