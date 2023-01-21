package com.example.sbertaste.mapper;

import com.example.sbertaste.dto.customer.CustomerRequestDto;
import com.example.sbertaste.dto.customer.CustomerResponseDto;
import com.example.sbertaste.dto.order.OrderDetailsDto;
import com.example.sbertaste.dto.order.OrderDto;
import com.example.sbertaste.dto.pizza.PizzaRequestDto;
import com.example.sbertaste.dto.pizza.PizzaResponseDto;
import com.example.sbertaste.model.CustomerEntity;
import com.example.sbertaste.model.OrderEntity;
import com.example.sbertaste.model.PizzaEntity;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrikaBeanMapper extends ConfigurableMapper {

    private MapperFactory factory;

    private final ApplicationContext applicationContext;

    public OrikaBeanMapper(ApplicationContext applicationContext) {
        super(false);
        this.applicationContext = applicationContext;
        init();
    }

    @Override
    protected void configure(MapperFactory factory) {
        this.factory = factory;

        addAllSpringBeans(applicationContext);

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

        factory.classMap(OrderDetailsDto.class, OrderEntity.class)
                .byDefault()
                .register();

        factory.classMap(OrderEntity.class, OrderDto.class)
                .byDefault()
                .register();
    }

    public void addMapper(Mapper<?, ?> mapper) {
        factory.classMap(mapper.getAType(), mapper.getBType())
                .byDefault()
                .customize((Mapper) mapper)
                .register();
    }

    @SuppressWarnings("rawtypes")
    private void addAllSpringBeans(final ApplicationContext applicationContext) {
        Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
        for (Mapper mapper : mappers.values()) {
            addMapper(mapper);
        }
    }

}
