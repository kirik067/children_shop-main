package org.childrenshop.repository.impl;

import org.childrenshop.config.AppConfig;
import org.childrenshop.model.StockBalance;
import org.childrenshop.model.Toy;
import org.childrenshop.repository.Stock;
import org.childrenshop.utils.FileUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

public class StockImpl implements Stock {
    private static HashSet<StockBalance> balance = initCollection();

    private static StockImpl instance;

    private StockImpl(){}

    public static StockImpl getInstance() {
        if (instance == null) {
            instance = new StockImpl();
        }
        return instance;
    }

    @Override
    public int add(Object entity) {
        this.balance.add((StockBalance) entity);
        FileUtils.writeFile(((StockBalance) entity).toString() + "\n", AppConfig.getProperty("file.stock"), true);
        return ((StockBalance) entity).toyId();
    }

    @Override
    public void update(Object entity) {
        var currentPosition = this.balance.stream().filter(position -> position.toyId() == ((StockBalance) entity).toyId()).findAny().get();
        this.balance.remove(currentPosition);
        this.balance.add((StockBalance) entity);
        writeAll();
    }

    @Override
    public Optional findById(int id) {
        return this.balance.stream().filter(position -> position.toyId() == id).findAny();
    }

    @Override
    public void delete(Object entity) {
        this.balance.remove((StockBalance) entity);
        writeAll();
    }

    @Override
    public HashSet findAll() {
        return this.balance;
    }

    private void writeAll(){
        var positionList = new ArrayList<StockBalance>(this.balance);
        positionList.sort(Comparator.comparing(StockBalance::toyId));
        FileUtils.writeFile("", AppConfig.getProperty("file.stock"), false);
        positionList.forEach(position -> FileUtils.writeFile(position.toString() + "\n", AppConfig.getProperty("file.stock"), true));
    }

    private static HashSet<StockBalance> initCollection() {
        HashSet<StockBalance> stockBalance = new HashSet<>();
        ArrayList<String> data = FileUtils.readFile(AppConfig.getProperty("file.stock"));

        for (String line: data) {
            String[] values = line.split(";");
            int id = Integer.parseInt(values[0]);
            int quantity = Integer.parseInt(values[1]);
            stockBalance.add(new StockBalance(id, quantity));
        }

        return stockBalance;
    }
}
