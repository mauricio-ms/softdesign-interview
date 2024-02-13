package br.com.softdesign.interview.http;

import br.com.softdesign.interview.domain.books.Book;
import br.com.softdesign.interview.domain.books.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
class BooksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BooksController.class);
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> findBookById(@PathVariable Long id) {
        LOGGER.info("GET /{}", id);
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok(BookMapper.mapToDto(book)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto addBook(@RequestBody BookDto bookDto) {
        LOGGER.info("POST / {}", bookDto);
        Book newBook = bookService.add(BookMapper.mapToDomain(bookDto));
        return BookMapper.mapToDto(newBook);
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        LOGGER.info("PUT /{} {}", id, bookDto);
        try {
            Book newBook = bookService.update(id, BookMapper.mapToDomain(bookDto));
            return ResponseEntity.ok(BookMapper.mapToDto(newBook));
        } catch (Exception e) {
            LOGGER.error("Error updating book {}:", id, e);
            return ResponseEntity.badRequest().build();
        }
    }
}
