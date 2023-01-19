package com.example.sbertaste.controller;

import com.example.sbertaste.annotation.DtoField;
import com.example.sbertaste.annotation.EntityField;
import com.example.sbertaste.annotation.GenericController;
import com.example.sbertaste.dto.CommonDto;
import com.example.sbertaste.mapper.OrikaBeanMapper;
import com.example.sbertaste.model.CommonEntity;
import com.example.sbertaste.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@GenericController
public abstract class CommonController<E extends CommonEntity, D extends CommonDto> {

    @Autowired
    private OrikaBeanMapper mapper;
    @DtoField
    protected Class<D> dtoClass;
    @EntityField
    protected Class<E> entityClass;

    private final CommonService<E> service;

    public CommonController(CommonService<E> service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(description = "Get object by ID", method = "GetOne")
    public D getById(@PathVariable Integer id) {
        return mapper.map(service.getOne(id), dtoClass);
    }

    @PostMapping
    @Operation(description = "Create object", method = "Create")
    @ResponseStatus(HttpStatus.CREATED)
    public D create(@RequestBody D dto) {
        return mapper.map(
                service.create(mapper.map(dto, entityClass)),
                dtoClass
        );
    }

    @PutMapping("/{id}")
    @Operation(description = "Update object", method = "Update")
    public D update(@RequestBody D dto, @PathVariable Integer id) {
        return mapper.map(
                service.update(mapper.map(dto, entityClass), id),
                dtoClass
        );
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete object", method = "Delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/listAll")
    @Operation(description = "Get all objects", method = "GetAll")
    public List<D> getAll() {
        return mapper.mapAsList(service.listAll(), dtoClass);
    }

}
