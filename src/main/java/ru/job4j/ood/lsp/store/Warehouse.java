package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {
    private List<Food> products = new ArrayList<>();

    @Override
    public void add(Food food) {
        this.products.add(food);
    }

    @Override
    public List<Food> getProducts() {
        return products;
    }
}
