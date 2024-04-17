package com.example.hexagonalarquitecture.books.application.usecases.get;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.books.domain.model.Book;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookGetByIdCase;
import com.example.hexagonalarquitecture.books.domain.ports.out.BookResponse;
import com.example.hexagonalarquitecture.books.infrastructure.repository.BookRepository;

@Service
public class BookGetByIdCaseImpl implements BookGetByIdCase{
    @Autowired
    private BookRepository booksRepository;
    
    public ResponseEntity<BookResponse> getBookById(String id) {
        Optional<Book> bookById = booksRepository.findById(id);
        Book book = bookById.orElse(null);

        // Creamos la respuesta
        if (book != null) {
            List<Book> bookList = Collections.singletonList(book);
            String message = "El libro se ha cargado con éxito";
            BookResponse response = new BookResponse(bookList, "Success", "200", message);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            String message = "No se han encontrado libros con ese Id";
            BookResponse response = new BookResponse(List.of(), "Not Found", "404", message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
