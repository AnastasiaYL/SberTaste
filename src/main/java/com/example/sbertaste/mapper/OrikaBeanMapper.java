package com.example.sbertaste.mapper;

import com.example.sbertaste.dto.CustomerDto;
import com.example.sbertaste.dto.PizzaDto;
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

        factory.classMap(PizzaEntity.class, PizzaDto.class)
                .byDefault()
                .register();
        factory.classMap(PizzaDto.class, PizzaEntity.class)
                .byDefault()
                .register();
        factory.classMap(CustomerEntity.class, CustomerDto.class)
                .byDefault()
                .register();
        factory.classMap(CustomerDto.class, CustomerEntity.class)
                .byDefault()
                .register();

    }
}
