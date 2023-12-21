package ru.job4j.ood.lsp.store;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected static final int ONE_QUARTER_EXPIRED = 25;
    protected static final int THREE_QUARTERS_EXPIRED = 75;
    protected static final int FULL_EXPIRED = 100;
    protected static final double DISCOUNT = 0.2;

    protected List<Food> products = new ArrayList<>();

    public abstract void add(List<Food> food);

    public List<Food> getProducts() {
        return products;
    }
}