package com.example.sbertaste.service;

import com.example.sbertaste.model.RoleEntity;
import com.example.sbertaste.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends CommonService<RoleEntity> {
    public RoleService(RoleRepository repository) {
        super(repository);
    }
}
