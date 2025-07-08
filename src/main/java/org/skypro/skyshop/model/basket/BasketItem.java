package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BasketItem)) {
            return false;
        }
        BasketItem other = (BasketItem) obj;
        return Objects.equals(this.product, other.product) &&
                Objects.equals(this.amount, other.amount);
    }
}
