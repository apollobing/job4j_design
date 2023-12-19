package ru.job4j.ood.lsp.store;

import java.util.List;

public abstract class AbstractStore implements Store {
    public abstract void add(Food food);

    public abstract List<Food> getProducts();
}
