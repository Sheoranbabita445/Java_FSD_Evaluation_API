package com.springboot.libmanagement.service;



import com.springboot.libmanagement.dto.request.BookReqDto;
import com.springboot.libmanagement.dto.request.BookUpdateRequestDto;
import com.springboot.libmanagement.dto.response.BookResDto;
import com.springboot.libmanagement.exception.InvalidCallException;
import com.springboot.libmanagement.exception.ResourceNotFoundException;
import com.springboot.libmanagement.mapper.BookMapper;
import com.springboot.libmanagement.model.Book;
import com.springboot.libmanagement.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::convertEntityToResponseDto)
                .toList();
    }

    public BookResDto getBookByISBN(String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);

        if (book == null) {
            throw new ResourceNotFoundException("Book not found");
        }

        return BookMapper.convertEntityToResponseDto(book);
    }

    public void addBook(@Valid BookReqDto dto) {
        Book book = BookMapper.convertDtoToEntity(dto);
        bookRepository.save(book);
    }

    public BookResDto updateBookByIsbn(@Valid BookUpdateRequestDto dto) {
        Book book = bookRepository.findBookByIsbn(dto.isbn());

        if (book == null) {
            throw new ResourceNotFoundException("Book not found");
        }

        boolean flag = false;

        if (dto.title() != null && !dto.title().isBlank()) {
            book.setTitle(dto.title());
            flag = true;
        }

        if (dto.author() != null && !dto.author().isBlank()) {
            book.setAuthorName(dto.author());
            flag = true;
        }

        if (!flag) {
            throw new InvalidCallException("Update failed");
        }

        Book updatedBook = bookRepository.save(book);

        return BookMapper.convertEntityToResponseDto(updatedBook);
    }

    public void deleteBook(String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);

        if (book == null) {
            throw new ResourceNotFoundException("Book not found");
        }

        bookRepository.deleteBook(isbn);
    }
}