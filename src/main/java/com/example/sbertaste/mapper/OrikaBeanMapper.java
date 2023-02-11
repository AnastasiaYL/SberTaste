package com.example.sbertaste.mapper;

import com.example.sbertaste.dto.orderPosition.OrderPositionRequestDto;
import com.example.sbertaste.dto.orderPosition.OrderPositionResponseDto;
import com.example.sbertaste.dto.customer.CustomerRequestDto;
import com.example.sbertaste.dto.customer.CustomerResponseDto;
import com.example.sbertaste.dto.order.OrderDetailsDto;
import com.example.sbertaste.dto.pizza.PizzaRequestDto;
import com.example.sbertaste.dto.pizza.PizzaResponseDto;
import com.example.sbertaste.dto.role.RoleRequestDto;
import com.example.sbertaste.dto.role.RoleResponseDto;
import com.example.sbertaste.dto.user.UserResponseDto;
import com.example.sbertaste.model.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
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

        factory.classMap(OrderPositionRequestDto.class, OrderPositionResponseDto.class)
                .byDefault()
                .register();

        factory.classMap(UserEntity.class, UserResponseDto.class)
                .byDefault()
                .customize(new CustomMapper<>() {
                    @Override
                    public void mapAtoB(UserEntity userEntity, UserResponseDto userResponseDto, MappingContext context) {
                        userResponseDto.setRoleId(userEntity.getRole().getId());
                    }
                })
                .register();

        factory.classMap(RoleEntity.class, RoleResponseDto.class)
                .byDefault()
                .register();

        factory.classMap(RoleRequestDto.class, RoleEntity.class)
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
