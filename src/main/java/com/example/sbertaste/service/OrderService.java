package com.example.sbertaste.service;

import com.example.sbertaste.dto.OrderPositionDto;
import com.example.sbertaste.dto.order.Cart;
import com.example.sbertaste.mapper.OrikaBeanMapper;
import com.example.sbertaste.model.OrderEntity;
import com.example.sbertaste.model.OrderPositionEntity;
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
    private final OrderRepository orderRepository;
    private final OrderPositionRepository orderPositionRepository;
    private final OrikaBeanMapper mapper;

    public OrderService(Cart cart, OrderRepository orderRepository,
                        OrderPositionRepository orderPositionRepository, OrikaBeanMapper mapper) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
        this.mapper = mapper;
    }

    public OrderPositionDto addPosition(OrderPositionDto orderPositionDto) {
        for (OrderPositionDto cartPosition : cart.getOrderPositions()) {
            if (cartPosition.equals(orderPositionDto)) {
                cartPosition.setQuantity(cartPosition.getQuantity() + orderPositionDto.getQuantity());
                cartPosition.setPrice(cartPosition.getPrice());
                return cartPosition;
            }
        }

        cart.getOrderPositions().add(orderPositionDto);

        return orderPositionDto;
    }

    public List<OrderPositionDto> getPositions() {
        return cart.getOrderPositions();
    }

    @Transactional
    public OrderEntity placeOrder(OrderEntity orderEntity) {
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
