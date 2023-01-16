package com.example.sbertaste.controller;

import com.example.sbertaste.dto.CommonDto;
import com.example.sbertaste.model.CommonEntity;
import com.example.sbertaste.service.CommonService;
import org.springframework.web.bind.annotation.PostMapping;

public abstract class CommonController<E extends CommonEntity, D extends CommonDto> {

    private final CommonService<E> service;

    public CommonController(CommonService<E> service) {
        this.service = service;
    }

    @PostMapping
    public D create(D dto) {
        return mapToDto(service.create(mapToEntity(dto)));
    }

    public abstract E mapToEntity(D dto);

    public abstract D mapToDto(E entity);
}
