package br.com.softdesign.interview.domain.books;

import java.util.Optional;

public class Book {

    private final Long id;
    private String name;
    private String author;
    private final boolean rented;

    public Book(Long id, String name, String author, boolean rented) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.rented = rented;
    }

    public Book update(Book bookUpdate) {
        // TODO: Add check for rented
        Optional.ofNullable(bookUpdate.name)
                .ifPresent(v -> name = v);
        Optional.ofNullable(bookUpdate.author)
                .ifPresent(v -> author = v);
        return this;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String author() {
        return author;
    }

    public boolean rented() {
        return rented;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", rented=" + rented +
                '}';
    }
}
