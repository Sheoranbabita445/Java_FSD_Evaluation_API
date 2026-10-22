package com.springboot.libmanagement.repository;

import com.springboot.libmanagement.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b where b.isbn = ?1")
    Book findBookByIsbn(String isbn);

    @Query("delete from Book b where b.isbn = ?1")
    void deleteBook(String isbn);
}
