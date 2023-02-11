package com.example.sbertaste.service;

import com.example.sbertaste.dto.user.UserRequestTokenDto;
import com.example.sbertaste.model.UserEntity;
import com.example.sbertaste.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CommonService<UserEntity> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userRepository);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserEntity getByLogin(String login) {
        return ((UserRepository) repository).findUserEntityByLogin(login);
    }

    @Override
    public UserEntity save(UserEntity entity) {
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        return super.save(entity);
    }

    public boolean checkPassword(UserRequestTokenDto loginDto) {
        return bCryptPasswordEncoder.matches(loginDto.getPassword(), getByLogin(loginDto.getLogin()).getPassword());
    }
}
