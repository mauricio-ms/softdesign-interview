package br.com.softdesign.interview.domain.books;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    public Book add(Book book) {
        return repository.add(book);
    }

    public Book update(Long id, Book book) {
        return repository.update(id, book);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
