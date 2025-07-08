package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    public void givenStorageEmpty_whenSearchAny_thenReturnsEmptyList() {
        Mockito.doReturn(new ArrayList<Searchable>()).when(storageService).getAllSearchables();
        Collection<SearchResult> results = searchService.search("Any");
        Assertions.assertTrue(results.isEmpty());
    }

    @Test
    public void givenStorageHasTwo_whenSearchUnsatisfiable_thenReturnsEmptyList() {
        Mockito.doReturn(
                        List.of(
                                new SimpleProduct(UUID.randomUUID(), "Some", 123),
                                new SimpleProduct(UUID.randomUUID(), "Some", 456)
                        )
                )
                .when(storageService).getAllSearchables();
        Collection<SearchResult> results = searchService.search("Any");
        Assertions.assertTrue(results.isEmpty());
    }

    @Test
    public void givenTwoOfThreeInStorageSatisfiesSearch_whenSearchForTwo_thenReturnsExactTwo() {
        SimpleProduct Abc = new SimpleProduct(UUID.randomUUID(), "Abc", 123);
        SimpleProduct DAb = new SimpleProduct(UUID.randomUUID(), "DAb", 456);
        SimpleProduct Acb = new SimpleProduct(UUID.randomUUID(), "Acb", 789);
        Mockito.doReturn(List.of(Abc, Acb, DAb)).when(storageService).getAllSearchables();
        List<String> results = searchService.search("Ab")
                .stream().map(searchResult -> searchResult.getName()).toList();
        Assertions.assertEquals(2, results.size());
        Assertions.assertTrue(results.contains("Abc"));
        Assertions.assertTrue(results.contains("DAb"));
    }
}
