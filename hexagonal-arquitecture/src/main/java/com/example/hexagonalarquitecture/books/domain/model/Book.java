package com.example.hexagonalarquitecture.books.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document(value = "books")
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private String description;
    private int rate;
}
