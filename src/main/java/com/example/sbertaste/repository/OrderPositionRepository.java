package com.example.sbertaste.repository;

import com.example.sbertaste.model.OrderPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPositionRepository extends JpaRepository<OrderPositionEntity, Integer> {
}
