package br.com.softdesign.interview.http;

import br.com.softdesign.interview.domain.books.Book;
import br.com.softdesign.interview.domain.books.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
class BooksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto addBook(@RequestBody BookDto bookDto) {
        LOGGER.info("POST / " + bookDto);
        Book newBook = bookService.add(BookMapper.mapToDomain(bookDto));
        return BookMapper.mapToDto(newBook);
    }
}
