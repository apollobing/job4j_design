package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    public void whenAddToShopBananaOrangeKiwiAppleThenGetListOfOrangeKiwi() {
        Store store = new Shop();
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
        products = new ProductPeriod().get(products, LocalDate.of(2023, 12, 19));
        store.add(products);
        assertThat(store.getProducts()).containsExactly(orange, kiwi);
    }
}