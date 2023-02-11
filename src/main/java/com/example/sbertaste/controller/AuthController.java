package com.example.sbertaste.controller;

import com.example.sbertaste.dto.user.UserRequestTokenDto;
import com.example.sbertaste.dto.user.UserResponseTokenDto;
import com.example.sbertaste.model.UserEntity;
import com.example.sbertaste.security.JwtTokenUtil;
import com.example.sbertaste.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authorization", description = "Sign in")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public AuthController(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping(value = "/auth")
    public ResponseEntity<UserResponseTokenDto> createToken(@RequestBody UserRequestTokenDto requestTokenDto) {
        if (!userService.checkPassword(requestTokenDto)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponseTokenDto("Bad credentials"));
        }
        UserEntity userEntity = userService.getByLogin(requestTokenDto.getLogin());
        String token = jwtTokenUtil.generateToken(userEntity);
        System.out.println(userEntity.getAuthorities().toString());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return ResponseEntity.ok().body(new UserResponseTokenDto(token));
    }
}
