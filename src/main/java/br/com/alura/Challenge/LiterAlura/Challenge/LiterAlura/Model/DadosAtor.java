package br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.Model;

import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.records.Author;
import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.records.Results;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class DadosAtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nameAuthor;
    private Long birthYear;
    private Long deathYear;

    @OneToMany(mappedBy = "dadosAtor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DadosLivros> dadosLivrosList = new ArrayList<>();

    public DadosAtor() {}

    public DadosAtor(Results livros) {
        this.nameAuthor = livros.results().stream()
                .flatMap(r -> r.ator().stream())
                .findFirst()
                .map(Author::name)
                .orElse(null);

        this.birthYear = (long) livros.results().stream()
                .flatMap(r -> r.ator().stream())
                .mapToInt(Author::birthYear)
                .distinct()
                .findFirst()
                .orElse(0);

        this.deathYear = (long) livros.results().stream()
                .flatMap(r -> r.ator().stream())
                .mapToInt(Author::deathYear)
                .distinct()
                .findFirst()
                .orElse(0);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public Long getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Long deathYear) {
        this.deathYear = deathYear;
    }

    public Long getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Long birthYear) {
        this.birthYear = birthYear;
    }

    public List<DadosLivros> getDadosLivrosList() {
        return dadosLivrosList;
    }

    public void setDadosLivrosList(List<DadosLivros> dadosLivrosList) {
        this.dadosLivrosList = dadosLivrosList;
    }

}
