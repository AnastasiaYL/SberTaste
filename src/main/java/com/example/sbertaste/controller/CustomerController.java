package com.example.sbertaste.controller;

import com.example.sbertaste.dto.customer.CustomerRequestDto;
import com.example.sbertaste.dto.customer.CustomerResponseDto;
import com.example.sbertaste.model.CustomerEntity;
import com.example.sbertaste.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Customer API", description = "Operations on a customer")
public class CustomerController extends CommonController<CustomerEntity, CustomerRequestDto, CustomerResponseDto> {
    public CustomerController(CustomerService service) {
        super(service);
    }

}
