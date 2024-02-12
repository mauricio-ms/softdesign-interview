package br.com.softdesign.interview.http;

import br.com.softdesign.interview.domain.books.Book;

class BookMapper {

    public static Book mapToDomain(BookDto dto) {
        return new Book(
                null,
                dto.name(),
                dto.author(),
                dto.rented()
        );
    }

    public static BookDto mapToDto(Book book) {
        return new BookDto(
                book.id(),
                book.name(),
                book.author(),
                book.rented()
        );
    }
}
