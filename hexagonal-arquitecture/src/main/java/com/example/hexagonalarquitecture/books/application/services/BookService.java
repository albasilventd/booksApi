package com.example.hexagonalarquitecture.books.application.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.books.domain.model.Book;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookCreateCase;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookDeleteCase;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookGetByIdCase;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookGetListCase;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookUpdateCase;
import com.example.hexagonalarquitecture.books.domain.ports.out.BookResponse;

@Service
public class BookService implements BookCreateCase, BookGetListCase, BookGetByIdCase, BookUpdateCase, BookDeleteCase {

    private BookCreateCase bookCreateCase;
    private BookGetListCase bookGetListCase;
    private BookGetByIdCase bookGetByIdCase;
    private BookUpdateCase bookUpdateCase;
    private BookDeleteCase bookDeleteCase;

    public BookService(BookCreateCase bookCreateCase, BookGetListCase bookGetListCase, BookGetByIdCase bookGetByIdCase, BookUpdateCase bookUpdateCase, BookDeleteCase bookDeleteCase) {
        this.bookCreateCase = bookCreateCase;
        this.bookGetListCase = bookGetListCase;
        this.bookGetByIdCase = bookGetByIdCase;
        this.bookUpdateCase = bookUpdateCase;
        this.bookDeleteCase = bookDeleteCase;
    }

    @Override
    public ResponseEntity<BookResponse> createBook(Book book) {
        return bookCreateCase.createBook(book);
    }

    @Override
    public ResponseEntity<BookResponse> getBooks() {
        return bookGetListCase.getBooks();
    }

    @Override
    public ResponseEntity<BookResponse> getBookById(String id) {
        return bookGetByIdCase.getBookById(id);
    }

    @Override
    public ResponseEntity<BookResponse> updateBook(String id, Book bookpayload) {
        return bookUpdateCase.updateBook(id, bookpayload);
    }

    @Override
    public ResponseEntity<BookResponse> deleteBook(String id) {
        return bookDeleteCase.deleteBook(id);
    }
    
}
