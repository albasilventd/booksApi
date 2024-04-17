package com.example.hexagonalarquitecture.books.application.usecases.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.books.domain.model.Book;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookUpdateCase;
import com.example.hexagonalarquitecture.books.domain.ports.out.BookResponse;
import com.example.hexagonalarquitecture.books.infrastructure.repository.BookRepository;

@Service
public class BookUpdateCaseImpl implements BookUpdateCase{
    @Autowired
    private BookRepository booksRepository;
    
    public ResponseEntity<BookResponse> updateBook(String id, Book bookPayload) {
        Optional<Book> bookById = booksRepository.findById(id);

        if (bookById.isPresent()) {
            Book beforeBook = bookById.get();

            // Actualizamos el libro
            bookPayload.setId(id);
            if (bookPayload.getTitle() == null) {
                bookPayload.setTitle(beforeBook.getTitle());
            }

            if (bookPayload.getAuthor() == null) {
                bookPayload.setAuthor(beforeBook.getAuthor());
            }

            if (bookPayload.getGenre() == null) {
                bookPayload.setGenre(beforeBook.getGenre());
            }

            if (bookPayload.getDescription() == null) {
                bookPayload.setDescription(beforeBook.getDescription());
            }

            if (bookPayload.getRate() == 0) {
                bookPayload.setRate(beforeBook.getRate());
            }

            booksRepository.save(bookPayload);

            // Creamos la respuesta
            List<Book> bookList = new ArrayList<>();
            bookList.add(bookPayload);

            String message = "El libro se ha actualizado con éxito";
            BookResponse response = new BookResponse(bookList, "Success", "200", message);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            String message = "No se han encontrado libros con ese Id";
            BookResponse response = new BookResponse(List.of(), "Not Found", "404", message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
