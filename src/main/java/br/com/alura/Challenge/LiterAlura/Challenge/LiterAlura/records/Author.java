package br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Author(@JsonAlias("name") String name,
                     @JsonAlias("birth_year") int birthYear,
                     @JsonAlias("death_year") int deathYear) {
    @Override
    public String toString() {
        return  "name='" + name +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear;

    }
}

