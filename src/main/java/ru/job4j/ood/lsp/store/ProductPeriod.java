package ru.job4j.ood.lsp.store;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ProductPeriod {
    public List<Food> get(List<Food> food, LocalDate currentDate) {
        for (Food product : food) {
            int daysOnMarket = (int) ChronoUnit.DAYS.between(product.getCreateDate(), currentDate);
            int daysDiff = (int) ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate());
            product.setProductLife((double) (daysOnMarket * 100) / daysDiff);
        }
        return food;
    }
}