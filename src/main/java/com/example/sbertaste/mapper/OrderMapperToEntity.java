package com.example.sbertaste.mapper;

import com.example.sbertaste.dto.order.OrderDetailsDto;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.model.OrderEntity;
import com.example.sbertaste.service.DeliveryService;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class OrderMapperToEntity extends CustomMapper<OrderDetailsDto, OrderEntity> {

    private final DeliveryService deliveryService;

    public OrderMapperToEntity(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @Override
    public void mapAtoB(OrderDetailsDto orderDetailsDto, OrderEntity orderEntity, MappingContext context) {
        try {
            orderEntity.setDelivery(deliveryService.getOne(orderDetailsDto.getDeliveryTypeId()));

        } catch (STNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No delivery type with id %d", orderDetailsDto.getDeliveryTypeId()));
        }
    }

}