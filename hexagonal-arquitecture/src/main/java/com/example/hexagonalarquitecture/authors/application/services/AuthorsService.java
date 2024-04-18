package com.example.hexagonalarquitecture.authors.application.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.authors.domain.ports.in.AuthorGetByIdCase;
import com.example.hexagonalarquitecture.authors.domain.ports.in.AuthorGetListCase;
import com.example.hexagonalarquitecture.authors.domain.ports.out.AuthorResponse;

@Service
public class AuthorsService implements AuthorGetListCase, AuthorGetByIdCase {
    private AuthorGetListCase authorGetListCase;
    private AuthorGetByIdCase authorGetByIdCase;

    public AuthorsService(AuthorGetListCase authorGetListCase, AuthorGetByIdCase authorGetByIdCase){
        this.authorGetListCase = authorGetListCase;
        this.authorGetByIdCase = authorGetByIdCase;
    }

    @Override
    public ResponseEntity<AuthorResponse> getAuthors() {
        return authorGetListCase.getAuthors();
    }

    @Override
    public ResponseEntity<AuthorResponse> getAuthorById(String id) {
        return authorGetByIdCase.getAuthorById(id);
    }
}
