package com.example.hexagonalarquitecture.authors.application.usecases.get;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.authors.domain.model.Author;
import com.example.hexagonalarquitecture.authors.domain.ports.in.AuthorGetByIdCase;
import com.example.hexagonalarquitecture.authors.domain.ports.out.AuthorResponse;
import com.example.hexagonalarquitecture.authors.infrastructure.repository.AuthorsRepository;

@Service
public class AuthorGetByIdImpl implements AuthorGetByIdCase {
    @Autowired
    private AuthorsRepository authorsRepository;
    
    public ResponseEntity<AuthorResponse> getAuthorById(String id) {
        Optional<Author> authorById = authorsRepository.findById(id);
        Author author = authorById.orElse(null);

        // Creamos la respuesta
        if (author != null) {
            List<Author> bookList = Collections.singletonList(author);
            String message = "El libro se ha cargado con éxito";
            AuthorResponse response = new AuthorResponse(bookList, "Success", "200", message);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            String message = "No se han encontrado libros con ese Id";
            AuthorResponse response = new AuthorResponse(List.of(), "Not Found", "404", message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
