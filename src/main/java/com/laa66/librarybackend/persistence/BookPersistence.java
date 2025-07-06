package com.laa66.librarybackend.persistence;

import com.laa66.librarybackend.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookPersistence {

    List<Book> findAll();
    Optional<Book> findByID(Long id);
    List<Book> findByCategory(String categoryName);
    Book save(Book book);
    void deleteByID(Long id);
}
