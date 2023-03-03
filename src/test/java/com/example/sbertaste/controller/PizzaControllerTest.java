package com.example.sbertaste.controller;

import com.example.sbertaste.dto.pizza.PizzaRequestDto;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.repository.PizzaRepository;
import com.example.sbertaste.service.PizzaService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PizzaControllerTest {

    @Autowired
    @InjectMocks
    private PizzaController controller;
    @SpyBean
    private PizzaService service;
    @SpyBean
    private PizzaRepository repository;

    @Test
    @Order(1)
    void createHappyFlow() {
        PizzaRequestDto request = new PizzaRequestDto();
        request.setName("Pepperoni");
        request.setPrice(500);

        var response = controller.create(request);

        assertEquals(1, response.getId());
        assertEquals("Pepperoni", response.getName());
        assertEquals(500, response.getPrice());
    }

    @Test
    @SneakyThrows
    @Order(2)
    void update() {
        PizzaRequestDto request = new PizzaRequestDto();
        request.setName("Margarita");
        request.setPrice(600);

        var response = controller.update(request, 1);

        assertEquals(1, response.getId());
        assertEquals("Margarita", response.getName());
        assertEquals(600, response.getPrice());
    }

    @Test
    @SneakyThrows
    @Order(3)
    void getById() {
        var response = controller.getById(1);

        assertEquals(1, response.getId());
        assertEquals("Margarita", response.getName());
        assertEquals(600, response.getPrice());
    }

    @Test
    @Order(4)
    void getAll() {
        var response = controller.getAll();

        assertEquals(1, response.size());
    }

    @Test
    @Order(5)
    void uploadImage() throws Exception {
        MockMultipartFile testFile = new MockMultipartFile("file", "test.jpg",
                "image/jpg", "test".getBytes());
        controller.uploadImage(1, testFile);

        assertEquals("test.jpg", service.getOne(1).getImageName());
    }

    @Test
    @Order(6)
    void downloadImage() throws STNotFoundException {
        String imageFileName = controller.downloadImage(1).getFilename();

        assert imageFileName != null;
        assertTrue(imageFileName.contains("test.jpg"));
    }

    @Test
    @Order(7)
    void deleteById() {
        controller.deleteById(1);

        Mockito.verify(service, Mockito.times(1)).delete(1);
        Mockito.verify(repository, Mockito.times(1)).deleteById(1);
    }
}