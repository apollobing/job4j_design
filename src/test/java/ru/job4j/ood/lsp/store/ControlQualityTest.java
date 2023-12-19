package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    @Test
    public void whenAddToWarehouseBananaToShopOrangeToShopKiwiToTrashAppleThenGetListsContainThem() {
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
        List<Food> warehouse = controlQuality.store(banana);
        List<Food> shop = controlQuality.store(orange);
        List<Food> shopDiscount = controlQuality.store(kiwi);
        List<Food> trash = controlQuality.store(apple);
        assertThat(warehouse).containsExactly(banana);
        assertThat(warehouse.get(0).getCreateDate().toString()).isEqualTo(banana.getCreateDate().toString());
        assertThat(warehouse.get(0).getPrice()).isEqualTo(banana.getPrice());
        assertThat(shop).containsExactly(orange);
        assertThat(shop.get(0).getExpiryDate().toString()).isEqualTo(orange.getExpiryDate().toString());
        assertThat(shop.get(0).getPrice()).isEqualTo(orange.getPrice());
        assertThat(shopDiscount).containsExactly(kiwi);
        assertThat(shopDiscount.get(0).getDiscount()).isEqualTo(0.2);
        assertThat(shopDiscount.get(0).getPrice()).isEqualTo(12);
        assertThat(trash).containsExactly(apple);
        assertThat(trash.get(0).getCreateDate().toString()).isEqualTo(apple.getCreateDate().toString());
        assertThat(trash.get(0).getPrice()).isEqualTo(apple.getPrice());
    }
}