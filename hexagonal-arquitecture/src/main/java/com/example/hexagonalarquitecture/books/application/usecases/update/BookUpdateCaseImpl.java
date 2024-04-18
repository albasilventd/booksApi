package com.example.hexagonalarquitecture.books.application.usecases.update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.authors.domain.model.Author;
import com.example.hexagonalarquitecture.authors.infrastructure.repository.AuthorsRepository;
import com.example.hexagonalarquitecture.books.domain.model.Book;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookUpdateCase;
import com.example.hexagonalarquitecture.books.domain.ports.out.BookResponse;
import com.example.hexagonalarquitecture.books.infrastructure.repository.BookRepository;

@Service
public class BookUpdateCaseImpl implements BookUpdateCase {
    @Autowired
    private BookRepository booksRepository;
    @Autowired
    private AuthorsRepository authorsRepository;

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

            // Actualizamos el libro en el array de autores

            String authorName = beforeBook.getAuthor();
            Author bookAuthor = authorsRepository.findByName(authorName);
            List<Book> authorBooks = bookAuthor.getBooks();

            // Recorro el array y encuentro el libro a actualizar:
            for (Book authorBook : authorBooks) {
                if (authorBook.getId().equalsIgnoreCase(id)) {
                    if (bookPayload.getTitle() != null) {
                        authorBook.setTitle(bookPayload.getTitle());
                    }
                    // Si el autor en bookPayload no es nulo, actualiza el autor
                    if (bookPayload.getAuthor() != null) {
                        authorBook.setAuthor(bookPayload.getAuthor());
                    }
                    // Si el género en bookPayload no es nulo, actualiza el género
                    if (bookPayload.getGenre() != null) {
                        authorBook.setGenre(bookPayload.getGenre());
                    }
                    // Si la descripción en bookPayload no es nulo, actualiza la descripción
                    if (bookPayload.getDescription() != null) {
                        authorBook.setDescription(bookPayload.getDescription());
                    }
                    // Si el rate en bookPayload es diferente de cero, actualiza el rate
                    if (bookPayload.getRate() != 0) {
                        authorBook.setRate(bookPayload.getRate());
                    }
                    // Guarda el autor actualizado en la base de datos
                    authorsRepository.save(bookAuthor);
                    break;
                }
            }

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
