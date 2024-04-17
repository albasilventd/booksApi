package com.example.hexagonalarquitecture.books.application.usecases.get;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.books.domain.model.Book;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookGetListCase;
import com.example.hexagonalarquitecture.books.domain.ports.out.BookResponse;
import com.example.hexagonalarquitecture.books.infrastructure.repository.BookRepository;

@Service
public class BookGetListCaseImpl implements BookGetListCase {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<BookResponse> getBooks() {
        // Devolvemos una list ordenada alfabeticamente segun el titulo
        List<Book> bookList = bookRepository.findAll();
        List<Book> sortedBooks = bookList.stream()
                .sorted((book1, book2) -> book1.getTitle().compareToIgnoreCase(book2.getTitle()))
                .collect(Collectors.toList());

        // Creamos la respuesta
        String message = "";

        if (bookList.isEmpty()) {
            message = "No existen libros en la base de datos";
        } else {
            message = "Los libros se han cargado con éxito";
        }

        BookResponse response = new BookResponse(sortedBooks, "Success", "200", message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
