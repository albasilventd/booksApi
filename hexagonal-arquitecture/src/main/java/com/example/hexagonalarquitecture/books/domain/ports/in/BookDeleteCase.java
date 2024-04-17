package com.example.hexagonalarquitecture.books.domain.ports.in;

import org.springframework.http.ResponseEntity;

import com.example.hexagonalarquitecture.books.domain.ports.out.BookResponse;

public interface BookDeleteCase {
    ResponseEntity<BookResponse> deleteBook(String id);
}
