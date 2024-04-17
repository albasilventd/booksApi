package com.example.hexagonalarquitecture.books.domain.ports.in;

import org.springframework.http.ResponseEntity;

import com.example.hexagonalarquitecture.books.domain.model.Book;
import com.example.hexagonalarquitecture.books.domain.ports.out.BookResponse;

public interface BookUpdateCase {
    ResponseEntity<BookResponse> updateBook(String id, Book bookpayload);
}
