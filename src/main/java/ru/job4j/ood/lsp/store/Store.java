package ru.job4j.ood.lsp.store;

import java.util.List;

public interface Store {
    void add(Food food);

    List<Food> getProducts();
}
