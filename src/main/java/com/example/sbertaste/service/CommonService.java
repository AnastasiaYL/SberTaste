package com.example.sbertaste.service;

import com.example.sbertaste.model.CommonEntity;
import com.example.sbertaste.repository.CommonRepository;

import java.io.Serializable;

public abstract class CommonService<E extends CommonEntity> {

    private final CommonRepository<E> repository;

    public CommonService(CommonRepository<E> repository) {
        this.repository = repository;
    }

    public E create(E entity) {
        return repository.save(entity);
    }
}
