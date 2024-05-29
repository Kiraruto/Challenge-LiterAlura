package br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.repository;

import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.Model.DadosAtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAuthorAndBooksRepository extends JpaRepository<DadosAtor, Long> {
    List<DadosAtor> findAll();

    @Query("SELECT a FROM DadosAtor a WHERE :year BETWEEN a.birthYear AND a.deathYear")
    List<DadosAtor> atoresVivos(@Param("year") Long year);

    @Query("SELECT b.dadosAtor FROM DadosLivros b WHERE b.languages = :languages")
    List<DadosAtor> linguaLivros(@Param("languages") String languages);

}

