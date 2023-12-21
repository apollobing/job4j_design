package ru.job4j.ood.lsp.store;

import java.util.List;

public class Shop extends AbstractStore {
    @Override
    public void add(List<Food> food) {
        for (Food product : food) {
            if (product.getProductLife() >= ONE_QUARTER_EXPIRED && product.getProductLife() <= THREE_QUARTERS_EXPIRED) {
                products.add(product);
            } else if (product.getProductLife() > THREE_QUARTERS_EXPIRED && product.getProductLife() < FULL_EXPIRED) {
                product.setDiscount(DISCOUNT);
                product.setPrice(product.getPrice() - product.getPrice() * product.getDiscount());
                products.add(product);
            }
        }
    }
}
