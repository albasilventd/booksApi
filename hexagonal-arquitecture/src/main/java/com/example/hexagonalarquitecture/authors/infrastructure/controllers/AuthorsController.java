package com.example.hexagonalarquitecture.authors.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hexagonalarquitecture.authors.application.services.AuthorsService;
import com.example.hexagonalarquitecture.authors.domain.ports.out.AuthorResponse;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    private AuthorsService authorsService;

    @GetMapping
    public ResponseEntity<AuthorResponse> getAuthors(){
        return authorsService.getAuthors();
    }
}
