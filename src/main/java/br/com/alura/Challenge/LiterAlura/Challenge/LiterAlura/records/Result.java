package br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Result(@JsonAlias("title") String titulo,
                     @JsonIgnoreProperties(ignoreUnknown = true)
                     @JsonAlias("authors") List<Author> ator,
                     @JsonAlias("languages") List<String> lingua,
                     @JsonAlias("download_count") Integer numerosDownload) {

    @Override
    public String toString() {
        return  "titulo = " + titulo +
                ", ator = " + ator +
                ", lingua = " + lingua +
                ", numerosDownload = "  + numerosDownload
                + "\n";

    }
}

