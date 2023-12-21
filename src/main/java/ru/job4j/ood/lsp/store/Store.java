package ru.job4j.ood.lsp.store;

import java.util.List;

public interface Store {
    void add(List<Food> food);

    List<Food> getProducts();
}