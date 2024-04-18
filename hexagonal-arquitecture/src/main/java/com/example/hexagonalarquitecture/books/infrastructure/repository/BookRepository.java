package com.example.hexagonalarquitecture.books.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.hexagonalarquitecture.books.domain.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String>{
    boolean existsByTitle (String title);
    Book findByTitle (String title);
}
