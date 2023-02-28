package com.example.sbertaste.service;

import com.example.sbertaste.dto.order.DeliveryResponseDto;
import com.example.sbertaste.dto.orderPosition.OrderPositionRequestDto;
import com.example.sbertaste.dto.orderPosition.OrderPositionResponseDto;
import com.example.sbertaste.dto.order.Cart;
import com.example.sbertaste.exception.STCartEmptyException;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.mapper.OrikaBeanMapper;
import com.example.sbertaste.model.*;
import com.example.sbertaste.repository.OrderPositionRepository;
import com.example.sbertaste.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final Cart cart;
    private final PizzaService pizzaService;
    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final OrderPositionRepository orderPositionRepository;
    private final DeliveryService deliveryService;
    private final OrikaBeanMapper mapper;

    public OrderService(Cart cart, PizzaService pizzaService, CustomerService customerService, OrderRepository orderRepository,
                        OrderPositionRepository orderPositionRepository, DeliveryService deliveryService, OrikaBeanMapper mapper) {
        this.cart = cart;
        this.pizzaService = pizzaService;
        this.customerService = customerService;
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
        this.deliveryService = deliveryService;
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
    public OrderEntity placeOrder(OrderEntity orderEntity, String customerName) throws STCartEmptyException, STNotFoundException {
        if (cart.getOrderPositions().isEmpty()) {
            throw new STCartEmptyException("Cart cannot be empty");
        }

        orderEntity.setCreatedWhen(LocalDateTime.now());

        CustomerEntity customer = customerService.getCustomerByPhone(orderEntity.getPhone());
        if (Objects.isNull(customer)) {
            customer = new CustomerEntity(customerName, orderEntity.getPhone(), null);
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

        orderEntity.setDeliveryCost(getDeliveryCost(orderEntity.getDelivery().getId()));

        return savedOrder;
    }

    public Integer getDeliveryCost(Integer deliveryId) throws STNotFoundException {
        int sumOrderPositions = cart.getOrderPositions().stream()
                .mapToInt(position -> position.getPrice() * position.getQuantity())
                .sum();

        DeliveryEntity delivery = deliveryService.getOne(deliveryId);
        return sumOrderPositions < delivery.getMinimalCartForFreeDelivery() ? delivery.getCost() : 0;
    }


    public List<DeliveryResponseDto> getAllDeliveryCost() {
        int sumOrderPositions = cart.getOrderPositions().stream()
                .mapToInt(position -> position.getPrice() * position.getQuantity())
                .sum();

        List<DeliveryEntity> allDelivery = deliveryService.listAll();
        var result = allDelivery.stream()
                .map(delivery -> mapper.map(delivery, DeliveryResponseDto.class))
                .collect(Collectors.toList());
        result.forEach(deliveryResponseDto -> deliveryResponseDto.setCost(sumOrderPositions < deliveryResponseDto.getMinimalCartForFreeDelivery() ? deliveryResponseDto.getCost() : 0));

        return result;
    }
}
