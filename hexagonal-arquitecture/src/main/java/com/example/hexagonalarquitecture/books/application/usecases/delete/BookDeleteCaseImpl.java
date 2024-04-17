package com.example.hexagonalarquitecture.books.application.usecases.delete;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.books.domain.model.Book;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookDeleteCase;
import com.example.hexagonalarquitecture.books.domain.ports.out.BookResponse;
import com.example.hexagonalarquitecture.books.infrastructure.repository.BookRepository;

@Service
public class BookDeleteCaseImpl implements BookDeleteCase {
    @Autowired
    private BookRepository booksRepository;
    
    public ResponseEntity<BookResponse> deleteBook(String id) {
        Optional<Book> bookById = booksRepository.findById(id);
        Book book = bookById.orElse(null);

        if (book != null) {
            booksRepository.deleteById(id);

            // Creamos la respuesta
            List<Book> bookList = new ArrayList<>();
            bookList.add(book);
            String message = "El libro se ha borrado con éxito";
            BookResponse response = new BookResponse(bookList, "Success", "200", message);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            String message = "No se han encontrado libros con ese Id";
            BookResponse response = new BookResponse(List.of(), "Not Found", "404", message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}