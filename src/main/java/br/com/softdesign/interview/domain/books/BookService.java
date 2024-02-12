package br.com.softdesign.interview.domain.books;

import br.com.softdesign.interview.http.BookDto;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository repository;

    BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void add(BookDto bookDto) {
        repository.add(bookDto);
    }
}
