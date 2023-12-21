package ru.job4j.ood.lsp.store;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {

    public List<Store> store(List<Food> food, List<Store> stores, LocalDate currentDate) {
        food = new ProductPeriod().get(food, currentDate);
        for (Store store : stores) {
            store.add(food);
        }
        return stores;
    }
}