package com.example.sbertaste.controller;

import com.example.sbertaste.dto.CommonDto;
import com.example.sbertaste.model.CommonEntity;
import com.example.sbertaste.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CommonController<E extends CommonEntity, D extends CommonDto> {

    private final CommonService<E> service;

    public CommonController(CommonService<E> service) {
        this.service = service;
    }

    @Operation(description = "Get object by ID", method = "GetOne")
    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(mapToDto(service.getOne(id)));
    }

    @Operation(description = "Create object", method = "Create")
    @PostMapping
    public ResponseEntity<D> create(@RequestBody D dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDto(service.create(mapToEntity(dto))));
    }

    @Operation(description = "Update object", method = "Update")
    @PutMapping("/{id}")
    public ResponseEntity<D> update(@RequestBody D dto, @PathVariable Integer id) {
        dto.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapToDto(service.update(mapToEntity(dto))));
    }

    @Operation(description = "Delete object", method = "Delete")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        service.delete(id);
    }

    @Operation(description = "Get all objects", method = "GetAll")
    @GetMapping("/listAll")
    public ResponseEntity<List<D>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAll().stream().map(this::mapToDto).collect(Collectors.toList()));
    }

    public abstract E mapToEntity(D dto);

    public abstract D mapToDto(E entity);
}
