package com.example.sbertaste.controller;

import com.example.sbertaste.dto.order.Cart;
import com.example.sbertaste.dto.order.OrderDetailsDto;
import com.example.sbertaste.dto.orderPosition.OrderPositionRequestDto;
import com.example.sbertaste.dto.orderPosition.OrderPositionResponseDto;
import com.example.sbertaste.exception.STCartEmptyException;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.model.DeliveryEntity;
import com.example.sbertaste.model.PizzaEntity;
import com.example.sbertaste.service.DeliveryService;
import com.example.sbertaste.service.OrderService;
import com.example.sbertaste.service.PizzaService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderControllerTest {

    @Autowired
    @InjectMocks
    private OrderController controller;

    @SpyBean
    private OrderService service;

    @SpyBean
    private PizzaService pizzaService;

    @SpyBean
    private DeliveryService deliveryService;

    @Mock
    private Cart cart;

    @BeforeAll
    void prepare() {
        PizzaEntity newPizza = new PizzaEntity("Pepperoni", 600, "SberTasteLogo.jpeg",
                "/Users/anastasial/IdeaProjects/sbertaste/target/classes/SberTasteLogo.jpeg");
        pizzaService.save(newPizza);

        DeliveryEntity newDelivery = new DeliveryEntity("Courier", 200, 1500);
        deliveryService.save(newDelivery);
    }

    @Test
    void createOrderPosition() throws STNotFoundException {
        OrderPositionRequestDto request = new OrderPositionRequestDto();
        request.setPizzaId(1);
        request.setQuantity(3);

        OrderPositionRequestDto requestSecond = new OrderPositionRequestDto();
        requestSecond.setPizzaId(1);
        requestSecond.setQuantity(3);

        OrderPositionResponseDto response = controller.create(request);
        OrderPositionResponseDto responseSecond = controller.create(requestSecond);

        assertEquals(1, response.getPizzaId());
        assertEquals(6, response.getQuantity());
        assertEquals(pizzaService.getOne(1).getPrice(), pizzaService.getOne(response.getPizzaId()).getPrice());
    }

    @Test
    void getCart() throws STNotFoundException {
        OrderPositionRequestDto request = new OrderPositionRequestDto();
        request.setPizzaId(1);
        request.setQuantity(3);
        controller.create(request);

        int itemsInCart = controller.getCart().size();

        assertEquals(1, itemsInCart);
    }

    @Test
    void getDeliveryCost() throws STNotFoundException {
        OrderPositionRequestDto request = new OrderPositionRequestDto();
        request.setPizzaId(1);
        request.setQuantity(3);
        controller.create(request);

        int expectedDeliveryCost = controller.getDeliveryCost(1);

        assertEquals(0, expectedDeliveryCost);
    }

    @Test
    void placeOrder() throws STNotFoundException, STCartEmptyException {
        OrderPositionRequestDto request = new OrderPositionRequestDto();
        request.setPizzaId(1);
        request.setQuantity(3);
        controller.create(request);

        OrderDetailsDto orderDetails = new OrderDetailsDto();
        orderDetails.setName("customer");
        orderDetails.setPhone("+71234567890");
        orderDetails.setDeliveryAddress("Home");
        orderDetails.setDeliveryTypeId(1);
        orderDetails.setComment("none");

        var response = controller.placeOrder(orderDetails);

        assertEquals("+71234567890", response.getPhone());
    }

    @Test
    void removePosition() throws STNotFoundException {
        OrderPositionRequestDto request = new OrderPositionRequestDto();
        request.setPizzaId(1);
        request.setQuantity(3);
        controller.create(request);

        controller.removePositionFromCart(1);

        assertEquals(0, cart.getOrderPositions().size());
    }

    @Test
    void getAllDeliveryCost() throws STNotFoundException {
        OrderPositionRequestDto request = new OrderPositionRequestDto();
        request.setPizzaId(1);
        request.setQuantity(3);
        controller.create(request);

        assertEquals(1, controller.getAllDeliveryCost().size());
    }

    @Test
    void removePositionWithEmptyCart() throws STNotFoundException {

        assertThrows(STNotFoundException.class, () -> controller.removePositionFromCart(1));
    }
}
