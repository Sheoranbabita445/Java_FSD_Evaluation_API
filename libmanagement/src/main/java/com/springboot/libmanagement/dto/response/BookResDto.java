package com.springboot.libmanagement.dto.response;






public record BookResDto(
        Long bookId,
        String title,
        String author,
        String isbn,
        Integer publicationYear
) {
}
