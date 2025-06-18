package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
@Component
public class ProductBasket {
    private final Map<UUID, Integer> basket;

    ProductBasket() {
        basket = new HashMap<UUID, Integer>();
    }

    public void addProduct(UUID id) {
        Integer currentAmount = basket.computeIfAbsent(id, (t) -> 0);
        basket.put(id, currentAmount + 1);
    }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }
}
