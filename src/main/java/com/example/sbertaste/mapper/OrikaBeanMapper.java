package com.example.sbertaste.mapper;

import com.example.sbertaste.dto.customer.CustomerRequestDto;
import com.example.sbertaste.dto.customer.CustomerResponseDto;
import com.example.sbertaste.dto.pizza.PizzaRequestDto;
import com.example.sbertaste.dto.pizza.PizzaResponseDto;
import com.example.sbertaste.model.CustomerEntity;
import com.example.sbertaste.model.PizzaEntity;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class OrikaBeanMapper extends ConfigurableMapper {

    private MapperFactory factory;

    @Override
    protected void configure(MapperFactory factory) {
        this.factory = factory;

        factory.classMap(PizzaEntity.class, PizzaResponseDto.class)
                .byDefault()
                .register();

        factory.classMap(PizzaRequestDto.class, PizzaEntity.class)
                .byDefault()
                .register();

        factory.classMap(CustomerEntity.class, CustomerResponseDto.class)
                .byDefault()
                .register();

        factory.classMap(CustomerRequestDto.class, CustomerEntity.class)
                .byDefault()
                .register();

    }
}
