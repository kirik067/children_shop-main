package org.childrenshop.repository;

import java.util.HashSet;
import java.util.Optional;

public interface WonToys <T> {
    void add(int toyId);
    Optional<T> findById(int id);
    Optional<Integer> poll();
    Optional<Integer> peek();
    int countToyById(int id);
    int countToys();
}
