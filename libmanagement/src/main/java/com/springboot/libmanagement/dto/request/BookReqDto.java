package com.springboot.libmanagement.dto.request;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookReqDto(

        @NotBlank(message = "Title is required")
        @NotNull(message = "Title is required")
        String title,

        @NotBlank(message = "Author is required")
        @NotNull(message = "Author is required")
        String author,

        @NotBlank(message = "ISBN is required")
        @NotNull(message = "ISBN is required")
        String isbn,

        @NotNull(message = "Publication year is required")
        Integer publicationYear

) {
}

