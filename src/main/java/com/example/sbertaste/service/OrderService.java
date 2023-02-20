package com.example.sbertaste.service;

import com.example.sbertaste.dto.orderPosition.OrderPositionRequestDto;
import com.example.sbertaste.dto.orderPosition.OrderPositionResponseDto;
import com.example.sbertaste.dto.order.Cart;
import com.example.sbertaste.exception.STCartEmptyException;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.mapper.OrikaBeanMapper;
import com.example.sbertaste.model.CustomerEntity;
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
import java.util.Objects;

@Service
public class OrderService {

    private static final int DELIVERY_COST = 200;

    private final Cart cart;
    private final PizzaService pizzaService;
    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final OrderPositionRepository orderPositionRepository;
    private final OrikaBeanMapper mapper;

    public OrderService(Cart cart, PizzaService pizzaService, CustomerService customerService, OrderRepository orderRepository,
                        OrderPositionRepository orderPositionRepository, OrikaBeanMapper mapper) {
        this.cart = cart;
        this.pizzaService = pizzaService;
        this.customerService = customerService;
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
        this.mapper = mapper;
    }

    public OrderPositionResponseDto addPosition(OrderPositionRequestDto orderPositionRequestDto) throws STNotFoundException {
        OrderPositionResponseDto response = mapper.map(orderPositionRequestDto, OrderPositionResponseDto.class);

        for (OrderPositionResponseDto cartPosition : cart.getOrderPositions()) {
            if (cartPosition.equals(response)) {
                cartPosition.setQuantity(cartPosition.getQuantity() + response.getQuantity());
                cartPosition.setAmount(cartPosition.getQuantity() * cartPosition.getPrice());
                return cartPosition;
            }
        }

        PizzaEntity pizzaEntity = pizzaService.getOne(response.getPizzaId());
        response.setPrice(pizzaEntity.getPrice());
        response.setAmount(response.getQuantity() * response.getPrice());
        cart.getOrderPositions().add(response);

        return response;
    }

    public void removePosition(int pizzaId) throws STNotFoundException {

        for (OrderPositionResponseDto cartPosition : cart.getOrderPositions()) {
            if (cartPosition.getPizzaId() == pizzaId) {
                cart.getOrderPositions().remove(cartPosition);
                return;
            }
        }

        throw new STNotFoundException("No position with pizza_id " + pizzaId);
    }

    public void removeAllPositions() {
        cart.getOrderPositions().clear();
    }

    public List<OrderPositionResponseDto> getPositions() {
        return cart.getOrderPositions();
    }

    @Transactional
    public OrderEntity placeOrder(OrderEntity orderEntity) throws STCartEmptyException {
        if (cart.getOrderPositions().isEmpty()) {
            throw new STCartEmptyException("Cart cannot be empty");
        }

        orderEntity.setDeliveryCost(DELIVERY_COST);
        orderEntity.setCreatedWhen(LocalDateTime.now());

        CustomerEntity customer = customerService.getCustomerByPhone(orderEntity.getPhone());
        if (Objects.isNull(customer)) {
            customer = new CustomerEntity("No name", orderEntity.getPhone(), null);
            customerService.save(customer);
        }
        orderEntity.setCustomer(customer);

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
