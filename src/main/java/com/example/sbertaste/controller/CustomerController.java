package com.example.sbertaste.controller;

import com.example.sbertaste.dto.CustomerDto;
import com.example.sbertaste.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController extends CommonController<CustomerService, CustomerDto> {
    public CustomerController(CustomerService service) {
        super(service);
    }
}
