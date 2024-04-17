package com.example.hexagonalarquitecture.books.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.hexagonalarquitecture.books.application.services.BookService;
import com.example.hexagonalarquitecture.books.domain.model.Book;
import com.example.hexagonalarquitecture.books.domain.ports.out.BookResponse;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookResponse> createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping
    public ResponseEntity<BookResponse> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable String id,
            @RequestBody Book payload) {
        return bookService.updateBook(id, payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleleBook(@PathVariable String id) {
        return bookService.deleteBook(id);
    }
}
