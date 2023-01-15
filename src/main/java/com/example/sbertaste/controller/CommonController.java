package com.example.sbertaste.controller;

import com.example.sbertaste.dto.IDto;
import com.example.sbertaste.service.ICommonService;

public abstract class CommonController<S extends ICommonService<D>, D extends IDto> implements ICommonController<D> {

    private final S service;

    public CommonController(S service) {
        this.service = service;
    }

    @Override
    public D create(D dto) {
        return service.create(dto);
    }
}
