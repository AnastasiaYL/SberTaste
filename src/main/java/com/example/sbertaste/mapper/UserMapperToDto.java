package com.example.sbertaste.mapper;

import com.example.sbertaste.dto.user.UserResponseDto;
import com.example.sbertaste.model.UserEntity;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class UserMapperToDto extends CustomMapper<UserEntity, UserResponseDto> {

    @Override
    public void mapAtoB(UserEntity userEntity, UserResponseDto userResponseDto, MappingContext context) {
        userResponseDto.setId(userEntity.getId());
        userResponseDto.setLogin(userEntity.getLogin());
        userResponseDto.setPassword(userEntity.getPassword());
        userResponseDto.setName(userEntity.getName());
        userResponseDto.setRoleId(userEntity.getRole().getId());
    }
}
