package com.example.hexagonalarquitecture.books.application.usecases.create;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.authors.domain.model.Author;
import com.example.hexagonalarquitecture.authors.infrastructure.repository.AuthorsRepository;
import com.example.hexagonalarquitecture.books.domain.model.Book;
import com.example.hexagonalarquitecture.books.domain.ports.in.BookCreateCase;
import com.example.hexagonalarquitecture.books.domain.ports.out.BookResponse;
import com.example.hexagonalarquitecture.books.infrastructure.repository.BookRepository;

@Service
public class BookCreateCaseImpl implements BookCreateCase {
    @Autowired
    private BookRepository booksRepository;
    @Autowired
    private AuthorsRepository authorsRepository;
    
    public ResponseEntity<BookResponse> createBook(Book book) {
        String title = book.getTitle();
        if (title == null) {
            String message = "Error: No se puede añadir un libro sin especificar el título";
            BookResponse response = new BookResponse(List.of(), "Bad Request", "400", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (booksRepository.existsByTitle(title)) {
            String message = "Error: Este título ya existe en la base de datos";
            BookResponse response = new BookResponse(List.of(), "Conflict", "409", message);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else {
            Book newBook = booksRepository.save(book);

            //Creamos un nuevo autor y añadimos el libro a su array de libros

            if (newBook.getAuthor()!=null){
                String authorName = book.getAuthor();
                    if(!authorsRepository.existsByName(authorName)){
                        List <Book> authorBooks = new ArrayList<>();
                        authorBooks.add(book);
                        Author author = new Author(book.getAuthor(), authorBooks);
                        authorsRepository.save(author);
                    } else {
                        Author existingAuthor = authorsRepository.findByName(authorName);
                        String id = existingAuthor.getId();
                        if (existingAuthor != null) {
                            existingAuthor.setId(id);
                            existingAuthor.getBooks().add(book);
                            authorsRepository.save(existingAuthor);
                        }
                    }
            }

            // Creamos la respuesta
            List<Book> bookList = new ArrayList<>();
            bookList.add(newBook);

            String message = "El libro se ha guardado con éxito";
            BookResponse response = new BookResponse(bookList, "Success", "200", message);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}
