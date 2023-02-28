package com.example.sbertaste.controller;

import com.example.sbertaste.dto.user.UserRequestTokenDto;
import com.example.sbertaste.dto.user.UserResponseTokenDto;
import com.example.sbertaste.model.UserEntity;
import com.example.sbertaste.security.JwtTokenUtil;
import com.example.sbertaste.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
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

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/change-password")
    public ResponseEntity<String> changePassword(@RequestParam String newPassword) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authorized");
        }

        String login = user.getUsername();
        UserEntity userEntity = userService.getByLogin(login);
        return ResponseEntity.ok().body(userService.changePassword(userEntity, newPassword));
    }
}
