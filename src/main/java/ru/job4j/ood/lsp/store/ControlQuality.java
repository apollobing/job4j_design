package ru.job4j.ood.lsp.store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    public List<Store> store(List<Food> food, List<Store> stores, LocalDate currentDate) {
        food = new ProductPeriod().get(food, currentDate);
        for (Store store : stores) {
            store.add(food);
        }
        return stores;
    }

    public List<Store> resort(List<Store> stores, LocalDate targetDate) {
        List<Store> updatedStores = new ArrayList<>(stores);
        List<Food> updatedProducts = new ArrayList<>();
        for (Store store : stores) {
            updatedProducts.addAll(new ProductPeriod().get(store.getProducts(), targetDate));
        }
        if (updatedProducts.isEmpty()) {
            throw new IllegalArgumentException("To resort products, stores must contain at least one product.");
        }
        for (Store store : updatedStores) {
            store.getProducts().clear();
            store.add(updatedProducts);
        }
        return updatedStores;
    }
}