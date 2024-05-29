package br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.Model;


import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.records.Result;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class DadosLivros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titleBook;
    private Integer downloadCout;
    private String languages;

    @ManyToOne
    private DadosAtor dadosAtor;

    public DadosLivros() {}

    public DadosLivros(Result livros) {
        try {
            this.titleBook = livros.titulo();
            this.downloadCout = livros.numerosDownload();
            this.languages = livros.lingua().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public DadosAtor getDadosAtor() {
        return dadosAtor;
    }

    public void setDadosAtor(DadosAtor dadosAtor) {
        this.dadosAtor = dadosAtor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    public Integer getDownloadCout() {
        return downloadCout;
    }

    public void setDownloadCout(Integer downloadCout) {
        this.downloadCout = downloadCout;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
