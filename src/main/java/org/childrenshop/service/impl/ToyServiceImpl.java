package org.childrenshop.service.impl;

import org.childrenshop.model.Toy;
import org.childrenshop.repository.Toys;
import org.childrenshop.repository.impl.ToysImpl;
import org.childrenshop.service.ToyService;

import java.util.HashSet;
import java.util.Optional;

public class ToyServiceImpl implements ToyService {
    private static Toys toys = ToysImpl.getInstance();
    @Override
    public int add(Toy toy) {
        return this.toys.add(toy);
    }
    @Override
    public void update(Toy toy) {
        this.toys.update(toy);
    }
    @Override
    public Optional<Toy> findById(int id) {
        return this.toys.findById(id);
    }

    @Override
    public HashSet<Toy> findAll() {
        return this.toys.findAll();
    }

    @Override
    public void delete(Toy toy) {
        this.toys.delete(toy);
    }
}
