package com.example.sbertaste.controller;

import com.example.sbertaste.dto.IDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICommonController<D extends IDto> {

    @PostMapping
    D create(@RequestBody D dto);
}
