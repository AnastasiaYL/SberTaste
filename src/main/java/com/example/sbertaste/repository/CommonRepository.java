package com.example.sbertaste.repository;

import com.example.sbertaste.model.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<E extends IEntity<ID>, ID> extends JpaRepository<E, ID> {
}
