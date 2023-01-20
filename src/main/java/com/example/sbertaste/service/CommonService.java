package com.example.sbertaste.service;

import com.example.sbertaste.controller.exception.NotFoundException;
import com.example.sbertaste.model.CommonEntity;
import com.example.sbertaste.repository.CommonRepository;

import java.util.List;

public abstract class CommonService<E extends CommonEntity> {

    private final CommonRepository<E> repository;

    public CommonService(CommonRepository<E> repository) {
        this.repository = repository;
    }

    public E save(E entity) {
        return repository.save(entity);
    }

    public E getOne(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("No entity with id %d exists", id))
        );
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<E> listAll() {
        return repository.findAll();
    }
}
