package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountProduct extends Product {
    private int price;
    private int discount;

    public DiscountProduct(UUID id, String name, int price, int discount) {
        super(id, name);
        if (price <= 0) {
            throw new IllegalArgumentException("Price can't be lower than 1");
        }
        this.price = price;
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be from 0 to 100");
        }
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return (int) (((double) price) * (100 - discount) / 100);
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + getDiscount() + ")";
    }
}
