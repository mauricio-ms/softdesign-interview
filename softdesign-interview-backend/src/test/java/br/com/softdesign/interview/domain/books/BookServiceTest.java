package br.com.softdesign.interview.domain.books;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @Test
    void rentTest() {
        Long id = 1L;
        Book book = mock(Book.class);
        when(repository.findById(id))
                .thenReturn(Optional.of(book));

        service.rent(id);

        InOrder inOrder = inOrder(book, repository);
        inOrder.verify(repository)
                .findById(id);
        inOrder.verify(book)
                .rent();
        inOrder.verify(repository)
                .update(id, book);

        verifyNoMoreInteractions(book, repository);
    }

    @Test
    void rentAlreadyTest() {
        Long id = 1L;
        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.rent(id));

        verify(repository)
                .findById(id);
        verifyNoMoreInteractions(repository);
    }
}