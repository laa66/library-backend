package com.laa66.librarybackend.controller;

import com.laa66.librarybackend.entity.Book;
import com.laa66.librarybackend.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookByID(@PathVariable Long id) {
        return ResponseEntity.of(bookService.findByID(id));
    }

    @PostMapping("/{category_id}")
    public ResponseEntity<Book> createBook(@RequestBody Book book, @PathVariable("category_id") long categoryID) {
        Book savedBook = bookService.save(book, categoryID);
        return ResponseEntity.ok(savedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> findByCategory(@PathVariable String category) {
        List<Book> books = bookService.findByCategory(category);
        return ResponseEntity.ok(books);

    }

}
