package com.laa66.librarybackend.service;

import com.laa66.librarybackend.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findByID(Long id);
    List<Book> findByCategory(String category);
    Book save(Book book);
    void deleteByID(Long id);
}
