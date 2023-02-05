package com.example.sbertaste.controller;

import com.example.sbertaste.dto.role.RoleRequestDto;
import com.example.sbertaste.dto.role.RoleResponseDto;
import com.example.sbertaste.model.RoleEntity;
import com.example.sbertaste.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@Tag(name = "Role API", description = "Operations on a user's role")
public class RoleController extends CommonController<RoleEntity, RoleRequestDto, RoleResponseDto> {
    public RoleController(RoleService service) {
        super(service);
    }
}
