package com.example.sbertaste.service;

import com.example.sbertaste.model.UserEntity;
import com.example.sbertaste.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CommonService<UserEntity> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}
