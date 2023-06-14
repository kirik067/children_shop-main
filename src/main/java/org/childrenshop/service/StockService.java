package org.childrenshop.service;

import org.childrenshop.model.StockBalance;

import java.util.HashSet;
import java.util.Optional;

public interface StockService {
    int add(StockBalance position);
    void update(StockBalance position);
    Optional<StockBalance> findById(int id);
    HashSet<StockBalance> findAll();
    void delete(StockBalance position);
}
