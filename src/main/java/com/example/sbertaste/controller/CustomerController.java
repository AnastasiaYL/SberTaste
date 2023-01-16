package com.example.sbertaste.controller;

import com.example.sbertaste.dto.CustomerDto;
import com.example.sbertaste.model.CustomerEntity;
import com.example.sbertaste.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController extends CommonController<CustomerEntity, CustomerDto> {
    public CustomerController(CustomerService service) {
        super(service);
    }

    @Override
    public CustomerEntity mapToEntity(CustomerDto dto) {
        CustomerEntity customerEntity = new CustomerEntity(dto.getName(), dto.getPhone(), dto.getEmail());
        customerEntity.setId(dto.getId());
        return customerEntity;
    }

    @Override
    public CustomerDto mapToDto(CustomerEntity entity) {
        return new CustomerDto(entity.getId(), entity.getName(), entity.getPhone(), entity.getEmail());
    }
}
