package br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Results(List<Result> results) {
    @Override
    public String toString() {
        return "{" + results + '}';
    }
}

