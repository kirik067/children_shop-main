package org.childrenshop.service;

import org.childrenshop.model.Toy;

import java.util.HashSet;
import java.util.Optional;

public interface ToyService {
    int add(Toy toy);
    void update(Toy toy);
    Optional<Toy> findById(int id);
    HashSet<Toy> findAll();
    void delete(Toy toy);
}
