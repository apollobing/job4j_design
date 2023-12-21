package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    @Test
    public void whenAddToWarehouseBananaToShopOrangeToShopKiwiToTrashAppleThenGetListContainsThem() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = new ArrayList<>();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality();
        Food banana = new Banana("banana",
                LocalDate.of(2023, 12, 17),
                LocalDate.of(2024, 1, 18),
                10,
                0);
        Food orange = new Orange("orange",
                LocalDate.of(2023, 12, 10),
                LocalDate.of(2023, 12, 24),
                11,
                0);
        Food kiwi = new Kiwi("kiwi",
                LocalDate.of(2023, 12, 12),
                LocalDate.of(2023, 12, 21),
                15,
                0);
        Food apple = new Apple("apple",
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 16),
                5,
                0);
        List<Food> products = new ArrayList<>();
        products.add(banana);
        products.add(orange);
        products.add(kiwi);
        products.add(apple);
        stores = controlQuality.store(products, stores, LocalDate.of(2023, 12, 19));
        assertThat(stores.get(0).getProducts()).containsExactly(banana);
        assertThat(stores.get(1).getProducts()).containsExactly(orange, kiwi);
        assertThat(stores.get(1).getProducts().get(1).getDiscount()).isEqualTo(0.2);
        assertThat(stores.get(1).getProducts().get(1).getPrice()).isEqualTo(12);
        assertThat(stores.get(2).getProducts()).containsExactly(apple);
    }
}