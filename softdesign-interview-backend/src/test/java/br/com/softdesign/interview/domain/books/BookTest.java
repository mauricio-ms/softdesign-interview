package br.com.softdesign.interview.domain.books;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void updateTest() {
        Book book = new Book(1L, "The Lord of the Rings", "J.R.R. Tolkien", false);
        book.update(new Book(null, "The Chronicles of Narnia", "C.S. Lewis", true));

        assertEquals("The Chronicles of Narnia", book.name());
        assertEquals("C.S. Lewis", book.author());
        assertTrue(book.rented());
    }

    @Test
    void updateRentedTest() {
        Book book = new Book(1L, "The Lord of the Rings", "J.R.R. Tolkien", false);
        book.rent();

        Book bookUpdate = new Book(null, "The Chronicles of Narnia", "C.S. Lewis", true);
        assertThrows(IllegalStateException.class, () -> book.update(bookUpdate));
    }

    @Test
    void rentTest() {
        Book book = new Book(1L, "The Lord of the Rings", "J.R.R. Tolkien", false);
        book.rent();

        assertTrue(book.rented());
    }

    @Test
    void rentAlreadyRentedTest() {
        Book book = new Book(1L, "The Lord of the Rings", "J.R.R. Tolkien", false);
        book.rent();

        assertThrows(IllegalStateException.class, book::rent);
    }

    @Test
    void deleteTest() {
        Book book = new Book(1L, "The Lord of the Rings", "J.R.R. Tolkien", false);
        assertDoesNotThrow(book::delete);
    }

    @Test
    void deleteRentedTest() {
        Book book = new Book(1L, "The Lord of the Rings", "J.R.R. Tolkien", false);
        book.rent();

        assertThrows(IllegalStateException.class, book::delete);
    }
}