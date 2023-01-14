package com.example.sbertaste.service;

import com.example.sbertaste.dto.IDto;

public interface ICommonService<D extends IDto> {

    D create(D dto);

}
