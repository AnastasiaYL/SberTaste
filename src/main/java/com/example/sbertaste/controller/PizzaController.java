package com.example.sbertaste.controller;

import com.example.sbertaste.dto.pizza.PizzaRequestDto;
import com.example.sbertaste.dto.pizza.PizzaResponseDto;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.model.PizzaEntity;
import com.example.sbertaste.service.PizzaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pizza")
@Tag(name = "Pizza API", description = "Operations on a pizza")
public class PizzaController extends CommonController<PizzaEntity, PizzaRequestDto, PizzaResponseDto> {

    private final PizzaService service;

    public PizzaController(PizzaService service) {
        super(service);
        this.service = service;
    }

    @Override
    @SecurityRequirement(name = "Bearer Authentication")
    public PizzaResponseDto create(PizzaRequestDto dto) {
        return super.create(dto);
    }

    @Override
    @SecurityRequirement(name = "Bearer Authentication")
    public PizzaResponseDto update(PizzaRequestDto dto, Integer id) throws STNotFoundException {
        return super.update(dto, id);
    }

    @Override
    @SecurityRequirement(name = "Bearer Authentication")
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @PostMapping(value = "/image/upload/{pizzaId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @SecurityRequirement(name = "Bearer Authentication")
    public PizzaEntity uploadImage(@PathVariable int pizzaId, @RequestParam("File") MultipartFile image) throws Exception {
        return service.saveImage(image.getBytes(), image.getOriginalFilename(), pizzaId);
    }

    @GetMapping(value = "/image/download/{pizzaId}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public FileSystemResource downloadImage(@PathVariable int pizzaId) throws STNotFoundException {
        return service.findImageByPizzaId(pizzaId);
    }

}