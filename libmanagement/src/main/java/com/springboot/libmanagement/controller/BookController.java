package com.springboot.libmanagement.controller;


import com.springboot.libmanagement.dto.request.BookReqDto;
import com.springboot.libmanagement.dto.request.BookUpdateRequestDto;
import com.springboot.libmanagement.dto.response.BookResDto;
import com.springboot.libmanagement.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/book/all")
    public List<BookResDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/api/book/{isbn}")
    public BookResDto getBookByISBN(@PathVariable String isbn) {
        return bookService.getBookByISBN(isbn);
    }

    @PostMapping("/api/book/add")
    public void addBook(@Valid @RequestBody BookReqDto dto) {
        bookService.addBook(dto);
    }

    @PutMapping("/api/book/update")
    public BookResDto updateBook(
            @Valid @RequestBody BookUpdateRequestDto dto) {
        return bookService.updateBookByIsbn(dto);
    }

    @DeleteMapping("/api/book/delete/{isbn}")
    public void deleteBookByISBN(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }
}