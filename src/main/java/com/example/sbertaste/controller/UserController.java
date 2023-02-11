package com.example.sbertaste.controller;

import com.example.sbertaste.dto.user.UserRequestDto;
import com.example.sbertaste.dto.user.UserResponseDto;
import com.example.sbertaste.model.UserEntity;
import com.example.sbertaste.service.CommonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User API", description = "Operations on a user")
public class UserController extends CommonController<UserEntity, UserRequestDto, UserResponseDto> {
    public UserController(CommonService<UserEntity> service) {
        super(service);
    }
}
