package ru.job4j.ood.lsp.store;

import java.util.List;

public class Warehouse extends AbstractStore {
    @Override
    public void add(List<Food> food) {
        for (Food product : food) {
            if (product.getProductLife() < ONE_QUARTER_EXPIRED) {
                products.add(product);
            }
        }
    }
}