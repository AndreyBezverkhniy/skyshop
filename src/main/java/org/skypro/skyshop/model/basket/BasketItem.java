package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public class BasketItem {
    private Product product;
    private Integer amount;

    public BasketItem(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getAmount() {
        return amount;
    }
}
