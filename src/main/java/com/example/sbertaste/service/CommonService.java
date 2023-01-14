package com.example.sbertaste.service;

import com.example.sbertaste.dto.IDto;
import com.example.sbertaste.model.IEntity;
import com.example.sbertaste.repository.CommonRepository;

public abstract class CommonService<R extends CommonRepository<E, ID>, E extends IEntity<ID>, ID, D extends IDto> implements ICommonService<D> {

    private final R repository;

    public CommonService(R repository) {
        this.repository = repository;
    }

    @Override
    public D create(D dto) {
        E entity = mapToEntity(dto);
        assert entity != null;
        E saved = repository.save(entity);
        return mapToDto(saved);
    }

    public abstract E mapToEntity(D dto);

    public abstract D mapToDto(E entity);
}
