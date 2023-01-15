package com.example.sbertaste.repository;

import com.example.sbertaste.model.CustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CommonRepository<CustomerEntity, Integer> {
}
