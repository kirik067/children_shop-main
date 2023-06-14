package org.childrenshop.repository.impl;

import org.childrenshop.config.AppConfig;
import org.childrenshop.model.Toy;
import org.childrenshop.repository.WonToys;
import org.childrenshop.utils.FileUtils;

import java.util.*;
import java.util.stream.Collectors;

public class WonToysImpl implements WonToys {
    private static Deque<Integer> wonToys = new ArrayDeque<>();

    private static WonToysImpl instance;

    private WonToysImpl(){}

    public static WonToysImpl getInstance() {
        if (instance == null) {
            instance = new WonToysImpl();
        }
        return instance;
    }
    @Override
    public void add(int toyId) {
        this.wonToys.addLast(toyId);
        var test = 1;
    }

    @Override
    public Optional findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> poll() {
        return  Optional.ofNullable(this.wonToys.pollFirst());
    }

    @Override
    public Optional<Integer> peek() {
        return  Optional.ofNullable(this.wonToys.peekFirst());
    }

    @Override
    public int countToyById(int id) {
        return (int) wonToys.stream().filter(item -> item == id).count();
    }

    @Override
    public int countToys(){
        return this.wonToys.size();
    }

    private void writeAll(){
        FileUtils.writeFile("", AppConfig.getProperty("file.won_toys"), false);
        this.wonToys.forEach(toy -> FileUtils.writeFile(toy.toString() + "\n", AppConfig.getProperty("file.won_toys"), true));
    }

    private static HashSet<Toy> initCollection() {
        HashSet<Toy> toys = new HashSet<>();
        ArrayList<String> data = FileUtils.readFile(AppConfig.getProperty("file.toys"));

        for (String line: data) {
            String[] values = line.split(";");
            int id = Integer.parseInt(values[0]);
            String name = values[1];
            int heft = Integer.parseInt(values[2]);
            toys.add(new Toy(id, name, heft));
        }

        return toys;
    }
}
