package com.springboot.libmanagement.service;



import com.springboot.libmanagement.dto.request.BookReqDto;
import com.springboot.libmanagement.dto.request.BookUpdateRequestDto;
import com.springboot.libmanagement.dto.response.BookResDto;
import com.springboot.libmanagement.exception.InvalidCallException;
import com.springboot.libmanagement.exception.ResourceNotFoundException;
import com.springboot.libmanagement.model.Book;
import com.springboot.libmanagement.repository.BookRepository;
import com.springboot.libmanagement.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    // Task 1: Add Book
    @Test
    void addBookTest() {

        BookReqDto dto = new BookReqDto(
                "Java",
                "Babita",
                "ISBN001",
                2024
        );

        when(bookRepository.save(any(Book.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        bookService.addBook(dto);

        verify(bookRepository, times(1))
                .save(any(Book.class));

        verifyNoMoreInteractions(bookRepository);
    }

    // Task 2: Get All Books
    @Test
    void getAllBooksTest() {

        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Java");
        book1.setAuthorName("Babita");
        book1.setIsbn("ISBN001");
        book1.setPubicationYear(2024);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Spring Boot");
        book2.setAuthorName("Babita");
        book2.setIsbn("ISBN002");
        book2.setPubicationYear(2025);

        when(bookRepository.findAll())
                .thenReturn(List.of(book1, book2));

        List<BookResDto> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("Java", result.get(0).title());
        assertEquals("Babita", result.get(0).author());
        assertEquals("ISBN001", result.get(0).isbn());
        assertEquals(2024, result.get(0).publicationYear());

        assertEquals("Spring Boot", result.get(1).title());
        assertEquals("Babita", result.get(1).author());
        assertEquals("ISBN002", result.get(1).isbn());
        assertEquals(2025, result.get(1).publicationYear());

        verify(bookRepository, times(1)).findAll();

        verifyNoMoreInteractions(bookRepository);
    }

    // Task 3: Get Book By ISBN
    @Test
    void getBookByISBNTest() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Java");
        book.setAuthorName("Babita");
        book.setIsbn("ISBN001");
        book.setPubicationYear(2024);

        when(bookRepository.findBookByIsbn("ISBN001"))
                .thenReturn(book);

        BookResDto result = bookService.getBookByISBN("ISBN001");

        assertNotNull(result);
        assertEquals(1L, result.bookId());
        assertEquals("Java", result.title());
        assertEquals("Babita", result.author());
        assertEquals("ISBN001", result.isbn());
        assertEquals(2024, result.publicationYear());

        verify(bookRepository, times(1))
                .findBookByIsbn("ISBN001");

        verifyNoMoreInteractions(bookRepository);
    }

    // Task 4: Update Book
    @Test
    void updateBookTest() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Java");
        book.setAuthorName("Babita");
        book.setIsbn("ISBN001");
        book.setPubicationYear(2024);

        BookUpdateRequestDto dto = new BookUpdateRequestDto(
                "ISBN001",
                "Java With Spring Boot",
                "Babita"
        );

        when(bookRepository.findBookByIsbn("ISBN001"))
                .thenReturn(book);

        when(bookRepository.save(any(Book.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        BookResDto result = bookService.updateBookByIsbn(dto);

        assertNotNull(result);
        assertEquals("Java With Spring Boot", result.title());
        assertEquals("Babita", result.author());
        assertEquals("ISBN001", result.isbn());
        assertEquals(2024, result.publicationYear());

        verify(bookRepository, times(1))
                .findBookByIsbn("ISBN001");

        verify(bookRepository, times(1))
                .save(book);

        verifyNoMoreInteractions(bookRepository);
    }

    // Task 5: Delete Book
    @Test
    void deleteBookTest() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Java");
        book.setAuthorName("Babita");
        book.setIsbn("ISBN001");
        book.setPubicationYear(2024);

        when(bookRepository.findBookByIsbn("ISBN001"))
                .thenReturn(book);

        bookService.deleteBook("ISBN001");

        verify(bookRepository, times(1))
                .findBookByIsbn("ISBN001");

        verify(bookRepository, times(1))
                .deleteBook("ISBN001");

        verifyNoMoreInteractions(bookRepository);
    }

    // Task 6: Update Book - Not Found
    @Test
    void updateBookNotFoundTest() {

        BookUpdateRequestDto dto = new BookUpdateRequestDto(
                "ISBN999",
                "Java",
                "Babita"
        );

        when(bookRepository.findBookByIsbn("ISBN999"))
                .thenReturn(null);

        assertThrows(
                ResourceNotFoundException.class,
                () -> bookService.updateBookByIsbn(dto)
        );

        verify(bookRepository, times(1))
                .findBookByIsbn("ISBN999");

        verifyNoMoreInteractions(bookRepository);
    }

    // Task 7: Update Book - No Fields to Update
    @Test
    void updateBookNoFieldsTest() {

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Java");
        book.setAuthorName("Babita");
        book.setIsbn("ISBN001");
        book.setPubicationYear(2024);

        BookUpdateRequestDto dto = new BookUpdateRequestDto(
                "ISBN001",
                "",
                ""
        );

        when(bookRepository.findBookByIsbn("ISBN001"))
                .thenReturn(book);

        assertThrows(
                InvalidCallException.class,
                () -> bookService.updateBookByIsbn(dto)
        );

        verify(bookRepository, times(1))
                .findBookByIsbn("ISBN001");

        verifyNoMoreInteractions(bookRepository);
    }

    // Task 8: Delete Book - Not Found
    @Test
    void deleteBookNotFoundTest() {

        when(bookRepository.findBookByIsbn("ISBN999"))
                .thenReturn(null);

        assertThrows(
                ResourceNotFoundException.class,
                () -> bookService.deleteBook("ISBN999")
        );

        verify(bookRepository, times(1))
                .findBookByIsbn("ISBN999");

        verify(bookRepository, never())
                .deleteBook(anyString());

        verifyNoMoreInteractions(bookRepository);
    }
}