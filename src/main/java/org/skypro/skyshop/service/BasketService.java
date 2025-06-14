package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        Optional optional = storageService.getProductById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException();
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItemList;
        basketItemList = productBasket.getBasket().entrySet().stream()
                .map(entry -> new BasketItem(
                        storageService.getProductById(entry.getKey()).get(),
                        entry.getValue()
                )).toList();
        return new UserBasket(basketItemList);
    }
}
