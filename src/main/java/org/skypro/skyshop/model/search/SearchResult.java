package org.skypro.skyshop.model.search;

import java.util.UUID;

public class SearchResult {
    private final UUID id;
    private final String name;
    private final String contentType;

    SearchResult(UUID id, String name, String contentName) {
        this.id = id;
        this.name = name;
        this.contentType = contentName;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        UUID id = searchable.getId();
        String name = searchable.getSearchableName();
        String contentType = searchable.getSearchableType();
        return new SearchResult(id, name, contentType);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }
}
