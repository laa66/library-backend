package com.laa66.librarybackend.service;

import com.laa66.librarybackend.entity.Book;
import com.laa66.librarybackend.entity.Category;
import com.laa66.librarybackend.persistence.BookPersistence;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookPersistence bookPersistence;
    private final CategoryService categoryService;

    @Override
    public List<Book> findAll() {
        return bookPersistence.findAll();
    }

    @Override
    public Optional<Book> findByID(Long id) {
        return bookPersistence.findByID(id);
    }

    @Override
    public List<Book> findByCategory(String category) {
        return bookPersistence.findByCategory(category);
    }

    @Override
    public Book save(Book book, long categoryID) {
        Category category = categoryService.findByID(categoryID)
                .orElseThrow(() -> new RuntimeException("category not found")); // TODO: specific exception
        book.setCategory(category);
        return bookPersistence.save(book);
    }

    @Override
    public void deleteByID(Long id) {
        bookPersistence.deleteByID(id);
    }
}
