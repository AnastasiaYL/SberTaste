package com.example.sbertaste.repository;

import com.example.sbertaste.model.PizzaEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends CommonRepository<PizzaEntity, Integer> {
}
