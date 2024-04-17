package com.example.hexagonalarquitecture.books.domain.ports.out;

import java.util.ArrayList;
import java.util.List;

import com.example.hexagonalarquitecture.books.domain.model.Book;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {
    private List<String> metadata;
    private List<Book> books;

    public BookResponse(List<Book> books, String type, String code, String message) {
        this.metadata = new ArrayList<>();
        this.metadata.add("Type: " + type);
        this.metadata.add("Code: " + code);
        this.metadata.add("Message: " + message);
        ;

        //Si no hay libro, que no salga en la respuesta
        if (books != null && !books.isEmpty()) {
            this.books = books;
        } else {
            this.books = null;
        }
    }
}