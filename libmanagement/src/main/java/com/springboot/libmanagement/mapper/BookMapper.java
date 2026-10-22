package com.springboot.libmanagement.mapper;


import com.springboot.libmanagement.dto.request.BookReqDto;
import com.springboot.libmanagement.dto.response.BookResDto;
import com.springboot.libmanagement.model.Book;

public class BookMapper {

    // Request DTO -> Entity
    public static Book convertDtoToEntity(BookReqDto dto) {

        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthorName(dto.author());
        book.setIsbn(dto.isbn());
        book.setPubicationYear(dto.publicationYear());

        return book;
    }

    // Entity -> Response DTO
    public static BookResDto convertEntityToResponseDto(Book book) {

        return new BookResDto(
                book.getId(),
                book.getTitle(),
                book.getAuthorName(),
                book.getIsbn(),
                book.getPubicationYear()
        );
    }
}
