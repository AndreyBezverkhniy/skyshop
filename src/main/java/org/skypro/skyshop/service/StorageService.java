package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private Map<UUID, Product> products;
    private Map<UUID, Article> articles;

    public StorageService() {
        articles = new HashMap<UUID, Article>();
        products = new HashMap<UUID, Product>();
        Article[] articlesToAdd = {new Article(UUID.randomUUID(), "Dapple_double", "Text"), new Article(UUID.randomUUID(), "Dapple_double", "Text"), new Article(UUID.randomUUID(), "length_1", "Text"), new Article(UUID.randomUUID(), "length_333", "Text"), new Article(UUID.randomUUID(), "length_22", "Text"), new Article(UUID.randomUUID(), "length_4444", "Text"), new Article(UUID.randomUUID(), "length_long_alphabet_abc", "Text"), new Article(UUID.randomUUID(), "length_long_alphabet_acc", "Text"), new Article(UUID.randomUUID(), "length_long_alphabet_aac", "Text"),};
        for (Article article : articlesToAdd) {
            articles.put(article.getId(), article);
        }
        Product[] productsToAdd = {new SimpleProduct(UUID.randomUUID(), "SimpleProduct", 123), new FixPriceProduct(UUID.randomUUID(), "FixPriceProduct"), new DiscountProduct(UUID.randomUUID(), "DiscountProduct", 1000, 10),};
        for (Product product : productsToAdd) {
            products.put(product.getId(), product);
        }
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchables() {
        Collection<Searchable> searchables = new ArrayList<Searchable>();
        searchables.addAll(products.values());
        searchables.addAll(articles.values());
        return searchables;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}
