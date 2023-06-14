package org.childrenshop.repository;

import java.util.HashSet;
import java.util.Optional;

public interface Stock <T> {
    int add(T entity);
    void update(T entity);
    Optional<T> findById(int id);
    void delete(T entity);
    HashSet<T> findAll();
}
