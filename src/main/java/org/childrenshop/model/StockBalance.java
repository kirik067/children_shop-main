package org.childrenshop.model;

public record StockBalance(int toyId, int quantity) {
    @Override
    public String toString() {
        return toyId + ";" + quantity;
    }
}
