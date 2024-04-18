package com.example.hexagonalarquitecture.authors.domain.ports.out;

import java.util.ArrayList;
import java.util.List;

import com.example.hexagonalarquitecture.authors.domain.model.Author;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorResponse {
    private List<String> metadata;
    private List<Author> authors;

    public AuthorResponse(List<Author> authors, String type, String code, String message) {
        this.metadata = new ArrayList<>();
        this.metadata.add("Type: " + type);
        this.metadata.add("Code: " + code);
        this.metadata.add("Message: " + message);
        ;

        //Si no hay libro, que no salga en la respuesta
        if (authors != null && !authors.isEmpty()) {
            this.authors = authors;
        } else {
            this.authors = null;
        }
    }
}
