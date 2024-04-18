package com.example.hexagonalarquitecture.authors.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.hexagonalarquitecture.authors.domain.model.Author;

@Repository
public interface AuthorsRepository extends MongoRepository<Author, String>{
    boolean existsByName (String name);
    Author findByName (String name);
}

