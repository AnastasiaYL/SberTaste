package com.example.sbertaste.service;

import com.example.sbertaste.model.DeliveryEntity;
import com.example.sbertaste.repository.CommonRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService extends CommonService<DeliveryEntity> {
    public DeliveryService(CommonRepository<DeliveryEntity> repository) {
        super(repository);
    }
}
