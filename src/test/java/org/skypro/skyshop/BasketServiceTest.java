package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    public void whenTryToAddNonExistentProduct_thenThrowNoSuchProductException() {
        Mockito.doReturn(Optional.empty()).when(storageService).getProductById(any(UUID.class));
        Assertions.assertThrows(NoSuchProductException.class,
                () -> basketService.addProduct(UUID.randomUUID()));
    }

    @Test
    public void givenOneProductAndBasketWithIt_whenAddSameProduct_thenProductBasketCallAddProduct() {
        UUID uuid = UUID.randomUUID();
        SimpleProduct simpleProduct = new SimpleProduct(uuid, "simple", 123);
        Mockito.doReturn(Optional.of(simpleProduct)).when(storageService).getProductById(uuid);
        basketService.addProduct(uuid);
        Mockito.verify(productBasket).addProduct(uuid);
    }

    @Test
    public void givenProductBasketEmpty_whenBasketServiceGetUserBasket_thenReturnsEmpty() {
        Mockito.doReturn(new HashMap<UUID, Integer>()).when(productBasket).getBasket();
        UserBasket userBasket = basketService.getUserBasket();
        Assertions.assertTrue(userBasket.getBasketItemList().isEmpty());
    }

    @Test
    public void givenProductBasketIsNotEmpty_whenBasketServiceGetUserBasket_thenReturnsCorrectBasket() {
        Map<UUID, Integer> basket = new HashMap<UUID, Integer>();
        UUID uuid1 = UUID.randomUUID();
        SimpleProduct simpleProduct1 = new SimpleProduct(uuid1, "apple", 100);
        int productAmount1 = 1;
        basket.put(uuid1, 1);
        BasketItem basketItem1 = new BasketItem(simpleProduct1, productAmount1);
        UUID uuid2 = UUID.randomUUID();
        SimpleProduct simpleProduct2 = new SimpleProduct(uuid2, "banana", 200);
        int productAmount2 = 2;
        basket.put(uuid2, 2);
        BasketItem basketItem2 = new BasketItem(simpleProduct2, productAmount2);
        Mockito.doReturn(basket).when(productBasket).getBasket();
        Mockito.doReturn(Optional.of(simpleProduct1)).when(storageService).getProductById(uuid1);
        Mockito.doReturn(Optional.of(simpleProduct2)).when(storageService).getProductById(uuid2);
        UserBasket userBasket = basketService.getUserBasket();
        List<BasketItem> basketItemList = userBasket.getBasketItemList();
        Assertions.assertEquals(2, basketItemList.size());
        Assertions.assertTrue(basketItemList.contains(basketItem1));
        Assertions.assertTrue(basketItemList.contains(basketItem2));
    }
}
