package ru.job4j.ood.lsp.store;

import java.util.List;

public class ControlQuality {

    public List<Store> store(Food product, List<Store> stores) {
        for (Store store : stores) {
            store.add(product);
        }
        return stores;
    }
}
