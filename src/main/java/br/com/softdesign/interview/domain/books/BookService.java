package br.com.softdesign.interview.domain.books;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository repository;

    BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book add(Book book) {
        return repository.add(book);
    }
}
