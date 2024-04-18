package com.example.hexagonalarquitecture.authors.application.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hexagonalarquitecture.authors.domain.ports.in.AuthorGetListCase;
import com.example.hexagonalarquitecture.authors.domain.ports.out.AuthorResponse;

@Service
public class AuthorsService implements AuthorGetListCase {
    private AuthorGetListCase authorGetListCase;

    public AuthorsService(AuthorGetListCase authorGetListCase){
        this.authorGetListCase = authorGetListCase;
    }

    @Override
    public ResponseEntity<AuthorResponse> getAuthors() {
        return authorGetListCase.getAuthors();
    }
}
