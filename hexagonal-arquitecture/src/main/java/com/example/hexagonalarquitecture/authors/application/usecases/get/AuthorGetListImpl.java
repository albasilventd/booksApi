package com.example.hexagonalarquitecture.authors.application.usecases.get;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.authors.domain.model.Author;
import com.example.hexagonalarquitecture.authors.domain.ports.in.AuthorGetListCase;
import com.example.hexagonalarquitecture.authors.domain.ports.out.AuthorResponse;
import com.example.hexagonalarquitecture.authors.infrastructure.repository.AuthorsRepository;

@Service
public class AuthorGetListImpl implements AuthorGetListCase {
    @Autowired
    private AuthorsRepository authorRepository;

    public ResponseEntity<AuthorResponse> getAuthors() {
        // Devolvemos una list ordenada alfabeticamente segun el nombre
        List<Author> authorList = authorRepository.findAll();
        List<Author> sortedAuthors = authorList.stream()
                .sorted((author1, author2) -> author1.getName().compareToIgnoreCase(author2.getName()))
                .collect(Collectors.toList());

        // Creamos la respuesta
        String message = "";

        if (authorList.isEmpty()) {
            message = "No existen autores en la base de datos";
        } else {
            message = "Los autores se han cargado con éxito";
        }

        AuthorResponse response = new AuthorResponse(sortedAuthors, "Success", "200", message);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
