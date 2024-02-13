package br.com.softdesign.interview.http;

import br.com.softdesign.interview.domain.books.Book;
import br.com.softdesign.interview.domain.books.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
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
        try {
            return bookService.findById(id)
                    .map(book -> ResponseEntity.ok(BookMapper.mapToDto(book)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            LOGGER.error("Error finding book {}:", id, e);
            return ResponseEntity.of(
                            ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                    .build();
        }
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        LOGGER.info("POST / {}", bookDto);
        try {
            Book newBook = bookService.add(BookMapper.mapToDomain(bookDto));
            return ResponseEntity.ok(BookMapper.mapToDto(newBook));
        } catch (Exception e) {
            LOGGER.error("Error adding book {}:", bookDto, e);
            return ResponseEntity.of(
                            ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                    .build();
        }
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
            return ResponseEntity.of(
                            ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                    .build();
        }
    }

    @PatchMapping(
            path = "/{id}/rent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> rent(@PathVariable Long id) {
        LOGGER.info("PATCH /{}/rent", id);
        try {
            bookService.rent(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("Error renting book {}:", id, e);
            return ResponseEntity.of(
                            ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                    .build();
        }
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        LOGGER.info("DELETE /{}", id);
        try {
            bookService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("Error deleting book {}:", id, e);
            return ResponseEntity.of(
                            ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()))
                    .build();
        }
    }
}
