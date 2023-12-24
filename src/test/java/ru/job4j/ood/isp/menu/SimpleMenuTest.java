package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        Optional<Menu.MenuItemInfo> el1 = menu.select("Сходить в магазин");
        Optional<Menu.MenuItemInfo> el2 = menu.select("Купить продукты");
        Optional<Menu.MenuItemInfo> el3 = menu.select("Покормить собаку");
        assertThat(el1.isPresent()).isTrue();
        assertThat(el2.isPresent()).isTrue();
        assertThat(el3.isPresent()).isTrue();
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(el1.get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(el2.get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(el3.get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectItemThenGetItsChild() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        Optional<Menu.MenuItemInfo> el1 = menu.select("Купить продукты");
        assertThat(el1.isPresent()).isTrue();
        assertThat(el1.get().getChildren())
                .isEqualTo(List.of("Купить хлеб", "Купить молоко"));

    }

    @Test
    public void whenPrintThenGetEqualsMenuString() {
        MenuPrinter printMenu = new PrintMenu();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String separator = System.lineSeparator();
        String expected = "1.Сходить в магазин" + separator
                + "-- 1.1.Купить продукты" + separator
                + "---- 1.1.1.Купить хлеб" + separator
                + "---- 1.1.2.Купить молоко" + separator
                + "-- 1.2.Вернуть просрочку" + separator
                + "2.Покормить собаку";
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Сходить в магазин", "Вернуть просрочку", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        printMenu.print(menu);
        assertThat(outputStream.toString().trim()).isEqualTo(expected);
        System.setOut(System.out);
    }
}