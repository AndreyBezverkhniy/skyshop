package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Stream;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String pattern) {
        Collection<Searchable> allSearchables = storageService.getAllSearchables();
        Stream<Searchable> stream = allSearchables.stream();
        return stream.filter(searchable->searchable.getSearchTerm().contains(pattern))
                .map(SearchResult::fromSearchable)
                .toList();
    }

    public StorageService getStorageService() {
        return storageService;
    }
}
