package br.com.softdesign.interview.domain.books;

public class Book {

    private final Long id;
    private final String name;
    private final String author;
    private final boolean rented;

    public Book(Long id, String name, String author, boolean rented) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.rented = rented;
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
