package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    public void whenAddToTrashBananaOrangeKiwiAppleThenGetListOfApple() {
        Store store = new Trash();
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
        store.add(banana);
        store.add(orange);
        store.add(kiwi);
        store.add(apple);
        assertThat(store.getProducts()).containsExactly(apple);
    }
}