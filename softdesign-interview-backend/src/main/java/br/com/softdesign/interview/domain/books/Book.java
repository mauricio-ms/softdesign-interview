package br.com.softdesign.interview.domain.books;

import java.util.Objects;
import java.util.Optional;

public class Book {

    private final Long id;
    private String name;
    private String author;
    private Boolean rented;

    public Book(Long id, String name, String author, Boolean rented) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.rented = rented;
    }

    public void update(Book bookUpdate) {
        if (rented) {
            throw new IllegalStateException("Book " + id + " cannot be updated because it's rent.");
        }
        Optional.ofNullable(bookUpdate.name)
                .ifPresent(v -> name = v);
        Optional.ofNullable(bookUpdate.author)
                .ifPresent(v -> author = v);
        Optional.ofNullable(bookUpdate.rented)
                .ifPresent(v -> rented = v);
    }

    public void rent() {
        if (rented) {
            throw new IllegalStateException("Book " + id + " cannot be rent because it's already rent.");
        }
        rented = true;
    }

    public void delete() {
        if (rented) {
            throw new IllegalStateException("Book " + id + " cannot be deleted because it's rent.");
        }
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
        return Boolean.TRUE.equals(rented);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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
