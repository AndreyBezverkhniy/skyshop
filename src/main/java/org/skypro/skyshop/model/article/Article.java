package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    String name;
    String text;

    private final UUID id;

    public Article(UUID id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getSearchableName() {
        return name;
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getSearchableType() {
        return "ARTICLE";
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return name + "\n" + text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Article)) {
            return false;
        }
        Article other = (Article) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
