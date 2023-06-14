package org.childrenshop.service.impl;

import org.childrenshop.model.StockBalance;
import org.childrenshop.repository.Stock;
import org.childrenshop.repository.impl.StockImpl;
import org.childrenshop.service.StockService;

import java.util.HashSet;
import java.util.Optional;

public class StockServiceImpl implements StockService {
    private static Stock balance = StockImpl.getInstance();
    @Override
    public int add(StockBalance position) {
        return this.balance.add(position);
    }

    @Override
    public void update(StockBalance position) {
        this.balance.update(position);
    }

    @Override
    public Optional<StockBalance> findById(int id) {
        return this.balance.findById(id);
    }

    @Override
    public HashSet<StockBalance> findAll() {
        return this.balance.findAll();
    }

    @Override
    public void delete(StockBalance position) {
        this.balance.delete(position);
    }
}
