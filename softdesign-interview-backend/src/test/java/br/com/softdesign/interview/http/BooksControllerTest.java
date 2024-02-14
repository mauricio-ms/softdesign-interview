package br.com.softdesign.interview.http;

import br.com.softdesign.interview.domain.books.Book;
import br.com.softdesign.interview.domain.books.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BooksController.class)
class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void findBookByIdSuccessTest() throws Exception {
        Book book = new Book(1L, "The Lord of the Rings", "J.R.R. Tolkien", true);
        when(bookService.findById(book.id()))
                .thenReturn(Optional.of(book));
        mockMvc.perform(get("/books/{id}", book.id())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(book.id()))
                .andExpect(jsonPath("$.name").value(book.name()))
                .andExpect(jsonPath("$.author").value(book.author()))
                .andExpect(jsonPath("$.rented").value(book.rented()));
    }

    @Test
    void findBookByIdNotFoundTest() throws Exception {
        Long id = 1L;
        when(bookService.findById(id))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void findBookByIdInternalServerErrorTest() throws Exception {
        Long id = 1L;
        when(bookService.findById(id))
                .thenThrow(new RuntimeException("Test exception"));

        mockMvc.perform(get("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.detail").value("Test exception"));
    }

    @Test
    void searchSuccessTest() throws Exception {
        Book book = new Book(2L, "The Chronicles of Narnia", "C.S. Lewis", false);

        String query = "Narnia";
        when(bookService.search(query))
                .thenReturn(List.of(book));

        mockMvc.perform(get("/books/search")
                        .param("q", query)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(book.id()))
                .andExpect(jsonPath("$[0].name").value(book.name()))
                .andExpect(jsonPath("$[0].author").value(book.author()))
                .andExpect(jsonPath("$[0].rented").value(book.rented()));
    }

    @Test
    void searchInternalServerErrorTest() throws Exception {
        when(bookService.search(anyString()))
                .thenThrow(new RuntimeException("Test exception"));

        mockMvc.perform(get("/books/search")
                        .param("q", "query")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.detail").value("Test exception"));
    }

    @Test
    void addBookSuccessTest() throws Exception {
        Book createBookRequest = new Book(null, "The Lord of the Rings", "J.R.R. Tolkien", false);
        Book createdBook = new Book(1L, "The Lord of the Rings", "J.R.R. Tolkien", false);
        when(bookService.add(createBookRequest))
                .thenReturn(createdBook);

        String bookDtoJson = new ObjectMapper()
                .writeValueAsString(new BookDto(null, "The Lord of the Rings", "J.R.R. Tolkien", false));

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdBook.id()))
                .andExpect(jsonPath("$.name").value(createdBook.name()))
                .andExpect(jsonPath("$.author").value(createdBook.author()))
                .andExpect(jsonPath("$.rented").value(createdBook.rented()));
    }

    @Test
    void addBookInternalServerErrorTest() throws Exception {
        Book createBookRequest = new Book(null, "The Lord of the Rings", "J.R.R. Tolkien", false);
        when(bookService.add(createBookRequest))
                .thenThrow(new RuntimeException("Test exception"));

        String bookDtoJson = new ObjectMapper()
                .writeValueAsString(new BookDto(null, "The Lord of the Rings", "J.R.R. Tolkien", false));

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookDtoJson))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.detail").value("Test exception"));
    }

    @Test
    void updateBookSuccessTest() throws Exception {
        Book updateBookRequest = new Book(null, "The Chronicles of Narnia", "C.S. Lewis", false);
        Book updatedBook = new Book(1L, "The Chronicles of Narnia", "C.S. Lewis", false);
        when(bookService.update(updatedBook.id(), updateBookRequest))
                .thenReturn(updatedBook);

        String bookDtoJson = new ObjectMapper()
                .writeValueAsString(new BookDto(null, "The Chronicles of Narnia", "C.S. Lewis", false));

        mockMvc.perform(put("/books/{id}", updatedBook.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedBook.id()))
                .andExpect(jsonPath("$.name").value(updatedBook.name()))
                .andExpect(jsonPath("$.author").value(updatedBook.author()))
                .andExpect(jsonPath("$.rented").value(updatedBook.rented()));
    }

    @Test
    void updateBookInternalServerErrorTest() throws Exception {
        Long id = 2L;
        Book updateBookRequest = new Book(null, "The Chronicles of Narnia", "C.S. Lewis", false);
        when(bookService.update(id, updateBookRequest))
                .thenThrow(new RuntimeException("Test exception"));

        String bookDtoJson = new ObjectMapper()
                .writeValueAsString(new BookDto(null, "The Chronicles of Narnia", "C.S. Lewis", false));

        mockMvc.perform(put("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookDtoJson))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.detail").value("Test exception"));
    }

    @Test
    void rentBookSuccessTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(patch("/books/{id}/rent", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookService)
                .rent(id);
    }

    @Test
    void rentInternalServerErrorTest() throws Exception {
        Long id = 1L;
        doThrow(new RuntimeException("Test exception"))
                .when(bookService)
                .rent(id);

        mockMvc.perform(patch("/books/{id}/rent", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.detail").value("Test exception"));
    }

    @Test
    void deleteBookSuccessTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookService)
                .delete(id);
    }

    @Test
    void deleteBookInternalServerErrorTest() throws Exception {
        Long id = 1L;
        doThrow(new RuntimeException("Test exception"))
                .when(bookService)
                .delete(id);

        mockMvc.perform(delete("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.detail").value("Test exception"));
    }
}