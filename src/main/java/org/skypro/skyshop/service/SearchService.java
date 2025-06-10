package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String pattern) {
        Function<Searchable, String> func = Searchable::getSearchTerm;
        Function<String, Boolean> func2 = (str) -> str.contains(pattern);
        Function<Searchable, Boolean> composition = func2.compose(func);
        Predicate<Searchable> predicate = (searchable) -> composition.apply(searchable);
        Collection<Searchable> allSearchables = storageService.getAllSearchables();
        Stream<Searchable> stream = allSearchables.stream();
        Collection<SearchResult> results = stream.filter(predicate)
                .map(searchable -> SearchResult.fromSearchable(searchable))
                .toList();
        return results;
    }

    public StorageService getStorageService() {
        return storageService;
    }
}
