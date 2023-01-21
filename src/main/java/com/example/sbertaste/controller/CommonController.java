package com.example.sbertaste.controller;

import com.example.sbertaste.annotation.DtoResponseField;
import com.example.sbertaste.annotation.EntityField;
import com.example.sbertaste.annotation.GenericController;
import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.dto.CommonRequestDto;
import com.example.sbertaste.dto.CommonResponseDto;
import com.example.sbertaste.mapper.OrikaBeanMapper;
import com.example.sbertaste.model.CommonEntity;
import com.example.sbertaste.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@GenericController
public abstract class CommonController<E extends CommonEntity, DReq extends CommonRequestDto,
        DResp extends CommonResponseDto> {

    @Autowired
    private OrikaBeanMapper mapper;
    @EntityField
    protected Class<E> entityClass;
    @DtoResponseField
    protected Class<DResp> dtoResponseClass;

    private final CommonService<E> service;

    public CommonController(CommonService<E> service) {
        this.service = service;
    }

    @GetMapping("/listAll")
    @Operation(description = "Get all objects", method = "GetAll")
    public List<DResp> getAll() {
        return mapper.mapAsList(service.listAll(), dtoResponseClass);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get object by ID", method = "GetOne")
    public DResp getById(@NotNull @PathVariable Integer id) throws STNotFoundException {
        return mapper.map(service.getOne(id), dtoResponseClass);
    }

    @PostMapping
    @Operation(description = "Create object", method = "Create")
    @ResponseStatus(HttpStatus.CREATED)
    public DResp create(@NotNull @Validated @RequestBody DReq dto) {
        return mapper.map(
                service.save(mapper.map(dto, entityClass)),
                dtoResponseClass
        );
    }

    @PutMapping("/{id}")
    @Operation(description = "Update object", method = "Update")
    public DResp update(@NotNull @Validated @RequestBody DReq dto,
                        @NotNull @PathVariable Integer id) throws STNotFoundException {
        E entity = service.getOne(id);
        mapper.map(dto, entity);

        return mapper.map(
                service.save(entity),
                dtoResponseClass
        );
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete object", method = "Delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        service.delete(id);
    }

}
