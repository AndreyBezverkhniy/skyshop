package org.skypro.skyshop.model.basket;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class UserBasket {
    List<BasketItem> basketItemList;
    int total;

    public UserBasket(@NotNull List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
        this.total = basketItemList.stream()
                .map(basketItem ->
                        basketItem.getProduct().getPrice() * basketItem.getAmount())
                .reduce(0, (x, y) -> (x + y));
    }

    public int getTotal() {
        return total;
    }

    public List<BasketItem> getBasketItemList() {
        return Collections.unmodifiableList(basketItemList);
    }
}
