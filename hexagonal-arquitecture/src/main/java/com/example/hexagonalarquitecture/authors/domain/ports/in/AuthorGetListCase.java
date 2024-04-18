package com.example.hexagonalarquitecture.authors.domain.ports.in;

import org.springframework.http.ResponseEntity;

import com.example.hexagonalarquitecture.authors.domain.ports.out.AuthorResponse;

public interface AuthorGetListCase {
    ResponseEntity<AuthorResponse> getAuthors();
}
