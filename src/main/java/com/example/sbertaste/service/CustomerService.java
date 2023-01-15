package com.example.sbertaste.service;

import com.example.sbertaste.dto.CustomerDto;
import com.example.sbertaste.model.CustomerEntity;
import com.example.sbertaste.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends CommonService<CustomerRepository, CustomerEntity, Integer, CustomerDto> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
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
