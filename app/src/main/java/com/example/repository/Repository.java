package com.example.repository;

import java.util.List;

public interface Repository<ID, entityType> {
    void add(entityType entity);
    void delete(entityType entity);
    entityType searchById(String id);
    List<entityType> getAll();
    void update(entityType entity, entityType newEntity);
}
