package ru.job4j.ood.lsp.store;

import java.time.LocalDate;

public class Apple extends Food {
    public Apple(String name, LocalDate createDate, LocalDate expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
