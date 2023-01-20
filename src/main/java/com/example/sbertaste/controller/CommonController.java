package com.example.sbertaste.controller;

import com.example.sbertaste.annotation.DtoField;
import com.example.sbertaste.annotation.EntityField;
import com.example.sbertaste.annotation.GenericController;
import com.example.sbertaste.annotation.transfer.Exist;
import com.example.sbertaste.annotation.transfer.New;
import com.example.sbertaste.dto.CommonDto;
import com.example.sbertaste.mapper.OrikaBeanMapper;
import com.example.sbertaste.model.CommonEntity;
import com.example.sbertaste.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/listAll")
    @Operation(description = "Get all objects", method = "GetAll")
    public List<D> getAll() {
        return mapper.mapAsList(service.listAll(), dtoClass);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get object by ID", method = "GetOne")
    public D getById(@NotNull @PathVariable Integer id) {
        return mapper.map(service.getOne(id), dtoClass);
    }

    @PostMapping
    @Operation(description = "Create object", method = "Create")
    @ResponseStatus(HttpStatus.CREATED)
    public D create(@NotNull @RequestBody @Validated(New.class) D dto) {
        return mapper.map(
                service.create(mapper.map(dto, entityClass)),
                dtoClass
        );
    }

    @PutMapping
    @Operation(description = "Update object", method = "Update")
    public D update(@NotNull @RequestBody @Validated(Exist.class) D dto) {
        return mapper.map(
                service.update(mapper.map(dto, entityClass)),
                dtoClass
        );
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete object", method = "Delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        service.delete(id);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEmptyResultDataAccessException() {

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
