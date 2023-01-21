package com.example.sbertaste.mapper;

import com.example.sbertaste.dto.order.OrderDto;
import com.example.sbertaste.model.CustomerEntity;
import com.example.sbertaste.model.OrderEntity;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends CustomMapper<OrderDto, OrderEntity> {

    @Override
    public void mapAtoB(OrderDto orderDto, OrderEntity orderEntity, MappingContext context) {
        super.mapAtoB(orderDto, orderEntity, context);
    }

    @Override
    public void mapBtoA(OrderEntity orderEntity, OrderDto orderDto, MappingContext context) {
        CustomerEntity customer = orderEntity.getCustomer();
        orderDto.setCustomerId(customer != null ? customer.getId() : null);
    }
}
