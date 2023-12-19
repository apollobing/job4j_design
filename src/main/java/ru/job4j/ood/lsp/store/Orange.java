package ru.job4j.ood.lsp.store;

import java.time.LocalDate;

public class Orange extends Food {
    public Orange(String name, LocalDate createDate, LocalDate expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
