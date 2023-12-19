package ru.job4j.ood.lsp.store;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {
    public List<Food> store(Food product) {
        List<Food> products;
        int daysOnMarket = (int) ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now());
        int daysDiff = (int) ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate());
        double percentage = (double) (daysOnMarket * 100) / daysDiff;
        if (percentage < 25) {
            AbstractStore store = new Warehouse();
            store.add(product);
            products = store.getProducts();
        } else if (percentage >= 25 && percentage <= 75) {
            AbstractStore store = new Shop();
            store.add(product);
            products = store.getProducts();
        } else if (percentage > 75 && percentage < 100) {
            AbstractStore store = new Shop();
            product.setDiscount(0.2);
            product.setPrice(product.getPrice() - product.getPrice() * product.getDiscount());
            store.add(product);
            products = store.getProducts();
        } else {
            AbstractStore store = new Trash();
            store.add(product);
            products = store.getProducts();
        }
        return products;
    }
}
