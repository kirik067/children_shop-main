package org.childrenshop.service;

import org.childrenshop.model.Toy;
import org.childrenshop.service.impl.StockServiceImpl;
import org.childrenshop.service.impl.ToyServiceImpl;

import java.util.Optional;

public interface LotteryService {
    ToyService toyService = new ToyServiceImpl();
    StockService stockService = new StockServiceImpl();
    int holdLottery();
    int countWonToys();
    Optional<Integer> poll();
    Optional<Integer> peek();
    void releaseWonToy(Toy toy);
}
