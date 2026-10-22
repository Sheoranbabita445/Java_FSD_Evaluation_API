package com.springboot.libmanagement.dto.request;



public record BookUpdateRequestDto(
        String isbn,
        String title,
        String author
) {}
