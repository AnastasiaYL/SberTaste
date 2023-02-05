//package com.example.sbertaste.mapper;
//
//import com.example.sbertaste.dto.user.UserResponseDto;
//import com.example.sbertaste.exception.STNotFoundException;
//import com.example.sbertaste.model.UserEntity;
//import com.example.sbertaste.service.RoleService;
//import ma.glasnost.orika.CustomMapper;
//import ma.glasnost.orika.MappingContext;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//
//@Component
//public class UserMapper extends CustomMapper<UserResponseDto, UserEntity> {
//
//    private final RoleService roleService;
//
//    public UserMapper(RoleService roleService) {
//        this.roleService = roleService;
//    }
//
//    @Override
//    public void mapAtoB(UserResponseDto userResponseDto, UserEntity userEntity, MappingContext context) {
//        try {
//            userEntity.setRole(roleService.getOne(userResponseDto.getRoleId()));
//        } catch (STNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    String.format("No user with id %d exists", userResponseDto.getRoleId()));
//        }
//    }
//
//    @Override
//    public void mapBtoA(UserEntity userEntity, UserResponseDto userResponseDto, MappingContext context) {
//        userResponseDto.setRoleId(userEntity.getRole().getId());
//    }
//}
