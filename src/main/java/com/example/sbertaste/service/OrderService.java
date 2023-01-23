package com.example.sbertaste.service;

import com.example.sbertaste.dto.OrderPositionRequestDto;
import com.example.sbertaste.dto.OrderPositionResponseDto;
import com.example.sbertaste.dto.order.Cart;
import com.example.sbertaste.exception.STCartEmptyException;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.mapper.OrikaBeanMapper;
import com.example.sbertaste.model.OrderEntity;
import com.example.sbertaste.model.OrderPositionEntity;
import com.example.sbertaste.model.PizzaEntity;
import com.example.sbertaste.repository.OrderPositionRepository;
import com.example.sbertaste.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final Cart cart;
    private final PizzaService pizzaService;
    private final OrderRepository orderRepository;
    private final OrderPositionRepository orderPositionRepository;
    private final OrikaBeanMapper mapper;

    public OrderService(Cart cart, PizzaService pizzaService, OrderRepository orderRepository,
                        OrderPositionRepository orderPositionRepository, OrikaBeanMapper mapper) {
        this.cart = cart;
        this.pizzaService = pizzaService;
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
        this.mapper = mapper;
    }

    public OrderPositionResponseDto addPosition(OrderPositionRequestDto orderPositionDto) throws STNotFoundException {
        OrderPositionResponseDto response = mapper.map(orderPositionDto, OrderPositionResponseDto.class);

        PizzaEntity pizzaEntity = pizzaService.getOne(response.getPizzaId());
        response.setPrice(pizzaEntity.getPrice());

        for (OrderPositionResponseDto cartPosition : cart.getOrderPositions()) {
            if (cartPosition.equals(response)) {
                response.setQuantity(cartPosition.getQuantity() + response.getQuantity());
            }
        }

        response.setAmount(response.getQuantity() * response.getPrice());
        cart.setAmount(cart.getAmount() + response.getAmount());
        cart.getOrderPositions().add(response);

        return response;
    }

    public List<OrderPositionResponseDto> getPositions() {
        return cart.getOrderPositions();
    }

    @Transactional
    public OrderEntity placeOrder(OrderEntity orderEntity) throws STCartEmptyException {
        if (cart.getOrderPositions().isEmpty()) {
            throw new STCartEmptyException("Cart cannot be empty");
        }

        orderEntity.setAmount(cart.getAmount());
        orderEntity.setDeliveryCost(100D);
        orderEntity.setCreatedWhen(LocalDateTime.now());
        var savedOrder = orderRepository.save(orderEntity);

        orderEntity.setOrderPositions(mapper.mapAsList(cart.getOrderPositions(), OrderPositionEntity.class));
        for (OrderPositionEntity position : orderEntity.getOrderPositions()) {
            position.setOrder(savedOrder);
            orderPositionRepository.save(position);
        }

        cart.setOrderPositions(new ArrayList<>());

        return savedOrder;
    }
}
