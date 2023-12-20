package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    private List<Food> products = new ArrayList<>();

    @Override
    public void add(Food product) {
        double period = new ProductPeriod().get(product);
        if (period >= 25 && period <= 75) {
            products.add(product);
        } else if (period > 75 && period < 100) {
            product.setDiscount(0.2);
            product.setPrice(product.getPrice() - product.getPrice() * product.getDiscount());
            products.add(product);
        }
    }

    @Override
    public List<Food> getProducts() {
        return products;
    }
}
