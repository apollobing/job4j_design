package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {
    private List<Food> products = new ArrayList<>();

    @Override
    public void add(Food product) {
        if (new ProductPeriod().get(product) < 25) {
            products.add(product);
        }
    }

    @Override
    public List<Food> getProducts() {
        return products;
    }
}
