package com.example.sbertaste.service;

import com.example.sbertaste.model.CommonEntity;
import com.example.sbertaste.repository.CommonRepository;

import java.util.List;

public abstract class CommonService<E extends CommonEntity> {

    private final CommonRepository<E> repository;

    public CommonService(CommonRepository<E> repository) {
        this.repository = repository;
    }

    public E create(E entity) {
        return repository.save(entity);
    }

    public E getOne(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public E update(E entity) {
        return repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<E> listAll() {
        return repository.findAll();
    }
}
