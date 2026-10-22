package com.springboot.libmanagement.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String authorName;
    @Column(nullable = false, unique = true, length = 13)
    String isbn;

    @Max(value = 2026)
    @Column(nullable = false)
    int pubicationYear;

}
