package com.example.sbertaste.controller;

import com.example.sbertaste.dto.user.UserRequestTokenDto;
import com.example.sbertaste.model.RoleEntity;
import com.example.sbertaste.model.UserEntity;
import com.example.sbertaste.security.JwtTokenUtil;
import com.example.sbertaste.service.RoleService;
import com.example.sbertaste.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {

    @Autowired
    public MockMvc mvc;

    @Autowired
    @InjectMocks
    private AuthController controller;

    @SpyBean
    private JwtTokenUtil jwtTokenUtil;
    @SpyBean
    private UserService userService;

    @SpyBean
    private RoleService roleService;


    @BeforeAll
    void prepare() {
        RoleEntity role = new RoleEntity("MANAGER", "MANAGER");
        roleService.save(role);

        UserEntity userEntity = new UserEntity("user", "user", "name", role);
        userService.save(userEntity);
    }

    @Test
    void createTokenWithUnknownUser() {
        UserRequestTokenDto request = new UserRequestTokenDto();
        request.setLogin("TestUser");
        request.setPassword("user");

        assertThrows(ResponseStatusException.class, () -> controller.createToken(request));
    }

    @Test
    void createTokenWithValideUser() {
        UserRequestTokenDto request = new UserRequestTokenDto();
        request.setLogin("user");
        request.setPassword("user");

        assertEquals(HttpStatus.OK, controller.createToken(request).getStatusCode());
    }

    @Test
    void changePassword() throws Exception {
        UserRequestTokenDto request = new UserRequestTokenDto();
        request.setLogin("user");
        request.setPassword("user");

        String token = Objects.requireNonNull(controller.createToken(request).getBody()).getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        mvc.perform(put("/change-password").headers(headers)
                        .param("newPassword", "NewPassword"))
                .andExpect(status().is2xxSuccessful());
    }
}