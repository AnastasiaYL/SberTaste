package com.example.sbertaste.mapper;

import com.example.sbertaste.dto.user.UserResponseDto;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.model.UserEntity;
import com.example.sbertaste.service.RoleService;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserMapperToEntity extends CustomMapper<UserResponseDto, UserEntity> {

    private final RoleService roleService;

    public UserMapperToEntity(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void mapAtoB(UserResponseDto userResponseDto, UserEntity userEntity, MappingContext context) {
        userEntity.setId(userResponseDto.getId());
        userEntity.setLogin(userResponseDto.getLogin());
        userEntity.setPassword(userResponseDto.getPassword());
        userEntity.setName(userResponseDto.getName());
        try {
            userEntity.setRole(roleService.getOne(userResponseDto.getRoleId()));
        } catch (STNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("No user with id %d exists", userResponseDto.getRoleId()));
        }
    }
}
