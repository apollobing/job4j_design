package ru.job4j.ood.lsp.store;

import java.util.List;

public class Trash extends AbstractStore {
    @Override
    public void add(List<Food> food) {
        for (Food product : food) {
            if (product.getProductLife() >= FULL_EXPIRED) {
                products.add(product);
            }
        }
    }
}
