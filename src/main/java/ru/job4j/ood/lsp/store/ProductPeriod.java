package ru.job4j.ood.lsp.store;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProductPeriod {
    public double get(Food product) {
        int daysOnMarket = (int) ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now());
        int daysDiff = (int) ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate());
        return (double) (daysOnMarket * 100) / daysDiff;
    }
}
