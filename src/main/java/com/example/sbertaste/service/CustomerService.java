package com.example.sbertaste.service;

import com.example.sbertaste.dto.CustomerDto;
import com.example.sbertaste.model.CustomerEntity;
import com.example.sbertaste.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends CommonService<CustomerEntity> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

}
