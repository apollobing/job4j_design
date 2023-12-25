package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void whenResortThenGetShopContainsBananaAndTrashContainsOtherProducts() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = new ArrayList<>();
        List<Store> updatedStores;
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
        updatedStores = controlQuality.resort(stores, LocalDate.of(2024, 1, 12));
        assertThat(updatedStores.get(1).getProducts()).containsExactly(banana);
        assertThat(updatedStores.get(1).getProducts().get(0).getDiscount()).isEqualTo(0.2);
        assertThat(updatedStores.get(1).getProducts().get(0).getPrice()).isEqualTo(8);
        assertThat(updatedStores.get(2).getProducts()).containsExactly(orange, kiwi, apple);
        assertThat(updatedStores.get(0).getProducts()).isEmpty();
    }

    @Test
    public void whenResortWithoutProductsThenGetException() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(warehouse, shop, trash);
        ControlQuality controlQuality = new ControlQuality();
        Exception exception = assertThrows(
                Exception.class,
                () -> controlQuality.resort(stores, LocalDate.of(2024, 1, 12)));
        assertThat(exception.getMessage()).isEqualTo("To resort products, stores must contain at least one product.");
    }
}