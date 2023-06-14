package org.childrenshop.model;

public record Toy(int id, String name, int heft) {
    @Override
    public String toString() {
        return id + ";" + name + ";" + heft;
    }
}
