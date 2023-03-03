package com.example.sbertaste.controller;

import com.example.sbertaste.dto.customer.CustomerRequestDto;
import com.example.sbertaste.repository.CustomerRepository;
import com.example.sbertaste.repository.PizzaRepository;
import com.example.sbertaste.service.CustomerService;
import com.example.sbertaste.service.PizzaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class CustomerControllerTest {

    @Autowired
    @InjectMocks
    private CustomerController controller;
    @SpyBean
    private CustomerService service;
    @SpyBean
    private CustomerRepository repository;

    @Test
    void create() {
        CustomerRequestDto request = new CustomerRequestDto();
        request.setName("NewCustomer");
        request.setPhone("+71234567890");
        request.setEmail("email@mail.ru");

        var response = controller.create(request);

        assertEquals("NewCustomer", response.getName());
    }
}