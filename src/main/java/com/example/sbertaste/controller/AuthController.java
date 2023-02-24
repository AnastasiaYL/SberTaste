package com.example.sbertaste.controller;

import com.example.sbertaste.dto.user.UserRequestTokenDto;
import com.example.sbertaste.dto.user.UserResponseTokenDto;
import com.example.sbertaste.model.UserEntity;
import com.example.sbertaste.security.JwtTokenUtil;
import com.example.sbertaste.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Tag(name = "Authorization", description = "Sign in")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public AuthController(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<UserResponseTokenDto> createToken(@RequestBody UserRequestTokenDto requestTokenDto) {

        UserEntity userEntity = userService.getByLogin(requestTokenDto.getLogin());

        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No user with login %s exists", requestTokenDto.getLogin()));
        }

        if (!userService.checkPassword(requestTokenDto)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponseTokenDto("Bad credentials"));
        }

        String token = jwtTokenUtil.generateToken(userEntity);
        return ResponseEntity.ok().body(new UserResponseTokenDto(token));
    }
}
