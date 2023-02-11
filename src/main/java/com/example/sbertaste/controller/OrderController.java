package com.example.sbertaste.controller;

import com.example.sbertaste.dto.orderPosition.OrderPositionRequestDto;
import com.example.sbertaste.dto.orderPosition.OrderPositionResponseDto;
import com.example.sbertaste.dto.order.OrderDetailsDto;
import com.example.sbertaste.dto.order.OrderDto;
import com.example.sbertaste.exception.STCartEmptyException;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.mapper.OrikaBeanMapper;
import com.example.sbertaste.model.OrderEntity;
import com.example.sbertaste.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "Order API", description = "Operations on an order")
public class OrderController {

    private final OrderService service;
    private final OrikaBeanMapper mapper;


    public OrderController(OrderService service, OrikaBeanMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/cart/position")
    @Operation(description = "Get all objects", method = "GetAll")
    public List<OrderPositionResponseDto> getCart() {
        return service.getPositions();
    }

    @PostMapping("/cart/position")
    @Operation(description = "Create object", method = "Create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPositionResponseDto create(@NotNull @RequestBody @Validated OrderPositionRequestDto orderPositionDto) throws STNotFoundException {
        return service.addPosition(orderPositionDto);
    }

    @PostMapping("/place")
    public OrderDto placeOrder(@NotNull @RequestBody @Validated OrderDetailsDto dto) throws STCartEmptyException {
        return mapper.map(
                service.placeOrder(mapper.map(dto, OrderEntity.class)),
                OrderDto.class);
    }
}
