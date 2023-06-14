package org.childrenshop.repository.impl;

import org.childrenshop.config.AppConfig;
import org.childrenshop.model.Toy;
import org.childrenshop.repository.Toys;
import org.childrenshop.utils.FileUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

public class ToysImpl implements Toys {
    private static HashSet<Toy> toys = initCollection();

    private static ToysImpl instance;

    private ToysImpl(){}

    public static ToysImpl getInstance() {
        if (instance == null) {
            instance = new ToysImpl();
        }
        return instance;
    }

    @Override
    public int add(Object entity) {
        this.toys.add((Toy) entity);
        FileUtils.writeFile(((Toy) entity).toString() + "\n", AppConfig.getProperty("file.toys"), true);
        return ((Toy) entity).id();
    }

    @Override
    public void update(Object entity) {
         var currentToy = this.toys.stream().filter(toy -> toy.id() == ((Toy) entity).id()).findAny().get();
         this.toys.remove(currentToy);
         this.toys.add((Toy) entity);
         writeAll();
    }

    @Override
    public Optional findById(int id) {
        return this.toys.stream().filter(toy -> toy.id() == id).findAny();
    }

    @Override
    public void delete(Object entity) {
        this.toys.remove((Toy) entity);
        writeAll();
    }

    @Override
    public HashSet findAll() {
        return this.toys;
    }

    private void writeAll(){
        var toyList = new ArrayList<Toy>(this.toys);
        toyList.sort(Comparator.comparing(Toy::id));
        FileUtils.writeFile("", AppConfig.getProperty("file.toys"), false);
        toyList.forEach(toy -> FileUtils.writeFile(toy.toString() + "\n", AppConfig.getProperty("file.toys"), true));
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
